package com.snailwu.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import java.nio.charset.StandardCharsets;

/**
 * @author 吴庆龙
 * @date 2020/12/14 上午9:55
 */
@Configuration
@EnableRabbit
@ComponentScan("com.snailwu.rabbitmq.listener")
public class AnnotationRabbitMQConfig implements ApplicationContextAware {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    /**
     *
     * 注解 @NonNull/@Nullable 在程序运行的过程中不会起任何作用，只会在IDE、编译器、FindBugs检查、生成文档的时候有做提示
     */
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
//        template.setMandatory(true);
//        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//            @Override
//            public void returnedMessage(@NonNull Message message, int replyCode, @NonNull String replyText,
//                                        @NonNull String exchange, @NonNull String routingKey) {
//                // 消息不可达时，在此监听
//                byte[] body = message.getBody();
//                String msg = new String(body, StandardCharsets.UTF_8);
//                logger.warn("消息不可达监听: exchange:[{}] routingKey:[{}] message:[{}] replyCode:[{}] replyText:[{}]",
//                        exchange, routingKey, msg, replyCode, replyText);
//            }
//        });
        // 消费端 消息接收确认监听
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

    /**
     * 内部使用 SimpleMessageListenerContainer 来作为默认实现
     * bean name必须是 rabbitListenerContainerFactory
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setApplicationContext(applicationContext);
        factory.setConnectionFactory(connectionFactory);
        // 自动签收
//        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        // 手工签收
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        // 设置每个队列的限流
        factory.setPrefetchCount(2);

        // 配置消息转换器，默认使用 SimpleMessageConverter
        ContentTypeDelegatingMessageConverter messageConverter = new ContentTypeDelegatingMessageConverter();
//        messageConverter.addDelegate("application/json", new Jackson2JsonMessageConverter());
        factory.setMessageConverter(messageConverter);

        return factory;
    }

}
