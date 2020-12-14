package com.snailwu.rabbitmq.converter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

import java.nio.charset.StandardCharsets;

/**
 * @author 吴庆龙
 * @date 2020/12/10 下午4:45
 */
public class TextMessageConverter extends AbstractMessageConverter {
    /**
     * 生产者需要
     *
     * @param object            payload
     * @param messageProperties 消息属性
     * @return Message
     */
    @Override
    protected Message createMessage(Object object, MessageProperties messageProperties) {
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        messageProperties.setContentEncoding(StandardCharsets.UTF_8.name());
        String message = (String) object;
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        messageProperties.setContentLength(bytes.length);
        return new Message(bytes, messageProperties);
    }

    /**
     * 消费者需要
     *
     * @param message 消息
     * @return String
     */
    @Override
    public String fromMessage(Message message) throws MessageConversionException {
        byte[] body = message.getBody();
        return new String(body, StandardCharsets.UTF_8);
    }
}
