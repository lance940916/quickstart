package com.snailwu.rabbitmq.listener;

import com.snailwu.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.*;

import java.nio.charset.StandardCharsets;

/**
 * 声明在类上
 *
 * @author 吴庆龙
 * @date 2020/12/12 上午10:06
 */
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
//@Component
public class AnnotationMessageListener {

    @RabbitHandler
    public void handleMessage(byte[] body) {
        System.out.println("byte[] " + new String(body, StandardCharsets.UTF_8));
    }

    @RabbitHandler
    public void handleMessage(String message) {
        System.out.println("String " + message);
    }

    @RabbitHandler
    public void handleMessage(User user) {
        System.out.println("User " + user);
    }

}
