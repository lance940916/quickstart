package com.snailwu.rabbitmq;

import com.snailwu.rabbitmq.config.AnnotationRabbitMQConfig;
import com.snailwu.rabbitmq.config.RabbitMQConfig;
import com.snailwu.rabbitmq.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author: 吴庆龙
 * @date: 2020/2/10 12:49 下午
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {
                RabbitMQConfig.class,
//                AnnotationRabbitMQConfig.class,
        }
)
public class RabbitMQTest {

    @Resource
    private RabbitAdmin admin;
    @Resource
    private RabbitTemplate template;

    @Test
    public void deleteDeclare() {
        // 删除交换机
        admin.deleteExchange("");
        // 清空队列，第二个参数没用
        admin.purgeQueue("", true);
        // 删除队列
        admin.deleteQueue("");
        admin.deleteQueue("", true, true);
    }

    @Test
    public void declare() {
        // 声明交换机、队列和路由键
        Exchange exchange = ExchangeBuilder.directExchange("wu.users").durable(true).build();
        admin.declareExchange(exchange);
        Queue queue = QueueBuilder.durable("wu.tom").build();
        admin.declareQueue(queue);
        admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("wu.tom").noargs());
    }

    @Test
    public void sendNoRoutingKeyMsg() {
        // 声明交换机、队列和路由键
        Exchange exchange = ExchangeBuilder.directExchange("wu.exchange").durable(true).build();
        admin.declareExchange(exchange);
        Queue queue = QueueBuilder.durable("wu.queue").build();
        admin.declareQueue(queue);
        admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("wu.key").noargs());

        // 由于 RabbitTemplate Bean中没有定义交换机、队列以及路由键，所以这里发消息时需要指定
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("desc", "消息描述");
        messageProperties.setContentEncoding(StandardCharsets.UTF_8.name());
        String msg = "Hello RabbitTemplate.";
        Message message = new Message(msg.getBytes(StandardCharsets.UTF_8), messageProperties);
        template.send("wu.exchange", "wu.no_key", message);
    }

    @Test
    public void sendJsonToUserMsg() {
        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setHeader("__TypeId__", "com.snailwu.rabbitmq.entity.User");
        messageProperties.setHeader("__TypeId__", "user");
        messageProperties.setContentType("application/json");
        messageProperties.setContentEncoding(StandardCharsets.UTF_8.name());
        String msg = "{\"name\":\"Wu\",\"age\":10}";
        Message message = new Message(msg.getBytes(StandardCharsets.UTF_8), messageProperties);
        template.send("wu.users", "wu.mike", message);
    }

    @Test
    public void testSendUser() {
        User user = new User();
        user.setName("Jerry");
        user.setAge(20);
        template.convertAndSend("wu.users", "wu.jerry", user);
    }

}
