package com.snailwu.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import com.snailwu.rabbitmq.entity.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 声明在方法上可以注入 @RabbitListener 允许的参数，不需要 @RabbitHandler 注解
 *
 * @author 吴庆龙
 * @date 2020/12/12 上午10:06
 */
@Component
public class AnnotationMethodMessageListener {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            value = "annotation.queue",
                            durable = "true"
                    ),
                    exchange = @Exchange(
                            value = "annotation.exchange",
                            durable = "true"
                    ),
                    key = "annotation.direct"
            )
    )
    public void onMessage(Channel channel, Message message) throws IOException {
        byte[] body = message.getBody();
        System.out.println("RabbitListener " + new String(body, StandardCharsets.UTF_8));

        // 手工签收
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            value = "entity.queue",
                            durable = "true"
                    ),
                    exchange = @Exchange(
                            value = "entity.exchange",
                            durable = "true"
                    ),
                    key = "entity.direct"
            )
    )
    public void onUserMessage(@Payload User user, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag, Channel channel)
            throws IOException {
        System.out.println("RabbitListener " + user);

        // 手工签收
        channel.basicAck(deliveryTag, false);
    }

}
