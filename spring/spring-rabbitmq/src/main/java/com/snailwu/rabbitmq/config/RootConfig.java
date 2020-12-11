package com.snailwu.rabbitmq.config;

import com.snailwu.rabbitmq.entity.User;
import com.snailwu.rabbitmq.listener.SpringMessageListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 吴庆龙
 * @date 2020/5/22 1:41 下午
 */
@Configuration
public class RootConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setApplicationContext(applicationContext);
        return admin;
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("bean.exchange", true, false);
    }

    @Bean
    public Queue queue() {
        return new Queue("bean.queue", true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with("bean.abc");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setEncoding(StandardCharsets.UTF_8.name());
        return template;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.addQueues(queue());
        // 当前消费者的数量
        container.setConcurrentConsumers(1);
        // 最大消费者的数量
        container.setMaxConcurrentConsumers(5);
        // 是否重回队列
        container.setDefaultRequeueRejected(false);
        // 设置自动签收
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        // 设置 ConsumerTag 策略
        container.setConsumerTagStrategy(queue -> queue + "_" + UUID.randomUUID().toString());

        // 添加消费者，消费者不和队列绑定
//        MessageListenerAdapter adapter = new MessageListenerAdapter(new SpringMessageListener());
//        adapter.setMessageConverter(new TextMessageConverter());
//        container.setMessageListener(adapter);

        // 消费者队列和方法名一一进行匹配
//        MessageListenerAdapter adapter = new MessageListenerAdapter(new SpringMessageListener());
//        Map<String, String> queueOrTagToMethodName = new HashMap<>();
//        queueOrTagToMethodName.put(queue().getName(), "beanQueueMessage");
//        adapter.setQueueOrTagToMethodName(queueOrTagToMethodName);
//        container.setMessageListener(adapter);

        // 转换json为实体类
//        MessageListenerAdapter adapter = new MessageListenerAdapter(new SpringMessageListener());
//        adapter.setMessageConverter(new Jackson2JsonMessageConverter());
//        container.setMessageListener(adapter);


        MessageListenerAdapter adapter = new MessageListenerAdapter(new SpringMessageListener());
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper javaTypeMapper = (DefaultJackson2JavaTypeMapper)
                jsonMessageConverter.getJavaTypeMapper();
        // 使用 user 代替 com.snailwu.rabbitmq.entity.User
        Map<String, Class<?>> idClassMap = new HashMap<>();
        idClassMap.put("user", User.class);
        javaTypeMapper.setIdClassMapping(idClassMap);
        adapter.setMessageConverter(jsonMessageConverter);
        container.setMessageListener(adapter);

        return container;
    }

}
