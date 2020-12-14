package com.snailwu.rabbitmq.config;

import com.snailwu.rabbitmq.entity.User;
import com.snailwu.rabbitmq.listener.SpringMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
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
import org.springframework.lang.NonNull;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 传统方式的RabbitMQ配置
 *
 * @author 吴庆龙
 * @date 2020/5/22 1:41 下午
 */
@Configuration
public class RabbitMQConfig implements ApplicationContextAware {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * RabbitMQ连接工厂
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setApplicationContext(applicationContext);
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 链接超时时间
        factory.setConnectionTimeout(3000);
        // 设置链接的最大限制
        factory.setConnectionLimit(5);
        // 消息确认
        factory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.NONE);
        // 消息返回
        factory.setPublisherReturns(true);
        return factory;
    }

    /**
     * RabbitAdmin 可以实现 RabbitMQ 管控台的所有操作
     * 实际上是通过 RabbitTemplate 进行操作的
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setApplicationContext(applicationContext);
        return admin;
    }

    /**
     * 配置操作 RabbitMQ 的客户端
     * 并设置 RabbitTemplate 的公共属性
     * 可以设置公共的 Exchange Queue RoutingKey
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setEncoding(StandardCharsets.UTF_8.name());
        // 发送端 配置消息不可达时（无路由键），ReturnListener 会接收到该消息
        template.setMandatory(true);
        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                // 消息不可达时，在此监听
                byte[] body = message.getBody();
                String msg = new String(body, StandardCharsets.UTF_8);
                logger.warn("消息不可达监听: exchange:[{}] routingKey:[{}] message:[{}] replyCode:[{}] replyText:[{}]",
                        exchange, routingKey, msg, replyCode, replyText);
            }
        });
//        // 消费端 消息接收确认监听
//        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                // 消息确认结果
//                String correlationDataId = correlationData.getId();
//                logger.warn("消息确认监听: correlationDataId:[{}] ack:[{}], cause:[{}]", correlationDataId, ack, cause);
//            }
//        });
        return template;
    }

//    /**
//     * 通过Bean来声明交换机
//     */
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange("bean.exchange", true, false);
//    }
//
//    /**
//     * 通过Bean来声明队列
//     */
//    @Bean
//    public Queue queue() {
//        return new Queue("bean.queue", true);
//    }
//
//    /**
//     * 通过Bean来声明路由键
//     */
//    @Bean
//    public Binding binding() {
//        return BindingBuilder.bind(queue()).to(directExchange()).with("bean.key");
//    }

    /**
     * 配置消费者
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        // 添加消费队列
        container.addQueueNames("wu.queue", "wu.mike", "wu.tom", "wu.jerry");
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

        /*
            添加一个消费者，消费队列为：container.getQueueNames();
            默认的方法是：handleMessage
            默认的消息转换器是：SimpleMessageConverter
                1. 如果 properties 是 null 的话使用 byte[] 参数的方法来接收消息
                2. 如果 content_type 是以 text 开头的话，使用 String 参数的方法来接收消息
                3. 如果 content_type 是 application/x-java-serialized-object 的话，使用反序列化得到的对象进行接收
         */
//        MessageListenerAdapter adapter = new MessageListenerAdapter();
//        adapter.setDelegate(new SpringMessageListener());
//        container.setMessageListener(adapter);

        /*
            配置不同的队列对应不同的处理方法
            如果没有配置 wu.mike 队列对应的方法的话，默认是：handleMessage
         */
//        MessageListenerAdapter adapter = new MessageListenerAdapter(new SpringMessageListener());
//        Map<String, String> queueOrTagToMethodName = new HashMap<>();
//        queueOrTagToMethodName.put("wu.mike", "mikeMessage");
//        queueOrTagToMethodName.put("wu.tom", "tomMessage");
//        queueOrTagToMethodName.put("wu.jerry", "jerryMessage");
//        adapter.setQueueOrTagToMethodName(queueOrTagToMethodName);
//        container.setMessageListener(adapter);

        /*
            配置消息转换器，自动转为String类型的
            需要方法的参数是 String 类型的
         */
//        MessageListenerAdapter adapter = new MessageListenerAdapter();
//        adapter.setDelegate(new SpringMessageListener());
//        adapter.setMessageConverter(new TextMessageConverter());
//        container.setMessageListener(adapter);

        /*
            转换json为实体类
            需要配置两个参数：
                1. header 中加入 __TypeId__ = com.snailwu.rabbitmq.entity.User
                2. properties 中加入 content_type = application/json
            如果没有 __TypeId__ 则反序列后会自动使用 LinkedHashMap，可以使用 void handleMessage(Map map) 来接收消息
            如果没有 content_type 则是用 byte[] 参数的方法
         */
//        MessageListenerAdapter adapter = new MessageListenerAdapter();
//        adapter.setDelegate(new SpringMessageListener());
//        adapter.setMessageConverter(new Jackson2JsonMessageConverter());
//        container.setMessageListener(adapter);

        /*
            配置以实现在 __TypeId__ 中不需要输入类全名，直接使用别名即可
         */
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
