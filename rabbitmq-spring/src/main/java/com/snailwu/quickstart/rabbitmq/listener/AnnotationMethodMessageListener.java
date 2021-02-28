package com.snailwu.quickstart.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import com.snailwu.quickstart.rabbitmq.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 声明在方法上可以注入 @RabbitListener 允许的参数，不需要 @RabbitHandler 注解
 *
 * @author 吴庆龙
 * @date 2020/12/12 上午10:06
 */
@Component
public class AnnotationMethodMessageListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = {"wu.mike"})
    public void onMikeMessage(Channel channel, Message message) throws IOException {
        byte[] body = message.getBody();
        logger.warn("Mike收到消息：{}", new String(body, StandardCharsets.UTF_8));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 手工签收
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }

    @RabbitListener(queues = {"wu.tom"})
    public void onTomMessage(Channel channel, Message message, @Headers Map<String, Object> headers) throws IOException {
        byte[] body = message.getBody();
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            logger.warn("消息头：{} = {}", entry.getKey(), entry.getValue());
        }
        logger.warn("Tom收到消息：{}", new String(body, StandardCharsets.UTF_8));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 手工签收
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }

    @RabbitListener(queues = {"wu.jerry"})
    public void onJerryMessage(Channel channel, @Payload User user, @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag)
            throws IOException {
        logger.warn("Jerry收到消息：{}", user);

        // 手工签收
        channel.basicAck(deliveryTag, false);
    }

}
