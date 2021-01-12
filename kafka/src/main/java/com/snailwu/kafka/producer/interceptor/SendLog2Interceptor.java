package com.snailwu.kafka.producer.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author WuQinglong
 * @date 2021/1/5 10:27
 */
public class SendLog2Interceptor implements ProducerInterceptor<String, String> {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        log.info("生产者发送消息-验证拦截器顺序：topic:{}, key:{}, value:{}", record.topic(), record.key(), record.value());
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        log.info("Ack响应: {}", metadata);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
