package com.snailwu.rabbitmq.listener;

import com.snailwu.source.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author 吴庆龙
 * @date 2020/12/10 下午4:29
 */
public class SpringMessageListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    void handleMessage(byte[] bytes) {
        logger.warn("收到消息byte[]: {}", new String(bytes, StandardCharsets.UTF_8));
    }

    void handleMessage(String text) {
        logger.warn("收到消息String: {}", text);
    }

    void handleMessage(Map map) {
        logger.warn("收到消息Map: {}", map);
    }

    void handleMessage(User user) {
        logger.warn("收到消息User: {}", user);
    }

    void mikeMessage(byte[] bytes) {
        logger.warn("Mike收到消息byte[]: {}", new String(bytes, StandardCharsets.UTF_8));
    }

    void tomMessage(byte[] bytes) {
        logger.warn("Tom收到消息byte[]: {}", new String(bytes, StandardCharsets.UTF_8));
    }

    void jerryMessage(byte[] bytes) {
        logger.warn("Jerry收到消息byte[]: {}", new String(bytes, StandardCharsets.UTF_8));
    }

}
