package com.snailwu.quickstart.rabbitmq.listener;

import com.snailwu.quickstart.rabbitmq.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.nio.charset.StandardCharsets;

/**
 * 声明在类上
 * 根据配置的消息转换器进行方法的调用
 *
 * @author 吴庆龙
 * @date 2020/12/12 上午10:06
 */
@RabbitListener(queues = {"wu.mike"})
//@Component
public class AnnotationMessageListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void handleMessage(byte[] body) {
        logger.warn("收到消息byte[]：{}", new String(body, StandardCharsets.UTF_8));
    }

    @RabbitHandler
    public void handleMessage(String message) {
        logger.warn("收到消息String：{}",message);
    }

    @RabbitHandler
    public void handleMessage(User user) {
        logger.warn("收到消息User：{}", user);
    }

}
