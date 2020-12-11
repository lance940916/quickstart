package com.snailwu;

import com.snailwu.rabbitmq.config.RootConfig;
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
                RootConfig.class
        }
)
public class SpringTestTemplate {

    @Resource
    private RabbitAdmin rabbitAdmin;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private DirectExchange directExchange;

    @Test
    public void testRabbitAdmin() {
        Exchange exchange = new DirectExchange("spring.exchange", true, false);
        rabbitAdmin.declareExchange(exchange);

        Queue queue = new Queue("spring.queue", true);
        rabbitAdmin.declareQueue(queue);

        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("spring.#").noargs());
    }

    @Test
    public void testSendMessage() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("desc", "消息描述");
        messageProperties.setHeader("type", "自定义消息类型");
        Message message = new Message("Spring RabbitMQ!".getBytes(StandardCharsets.UTF_8), messageProperties);

        rabbitTemplate.send(directExchange.getName(), "bean.abc", message);
    }

    @Test
    public void testSendTextMessage() {
        rabbitTemplate.convertAndSend(directExchange.getName(), "bean.abc", "Hello Text RabbitMQ.");
    }

    @Test
    public void testSendJsonMessage() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("__TypeId__", "com.snailwu.rabbitmq.entity.User");
        messageProperties.setContentType("application/json");
        Message message = new Message("{\"name\":\"Wu\",\"age\":18}".getBytes(StandardCharsets.UTF_8), messageProperties);

        rabbitTemplate.send(directExchange.getName(), "bean.abc", message);
    }

}
