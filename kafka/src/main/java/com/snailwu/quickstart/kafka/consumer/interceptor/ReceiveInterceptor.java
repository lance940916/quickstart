package com.snailwu.quickstart.kafka.consumer.interceptor;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuQinglong
 * @date 2021/1/5 18:20
 */
public class ReceiveInterceptor implements ConsumerInterceptor<String, String> {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private final static long EXPIRE_INTERVAL = 10 * 1000;

    /**
     * 在 poll 方法返回之前调用
     * 这里可以实现一个消息 TTL 过期时间
     */
    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        records.forEach(record -> log.info("消费拦截器-收到消息：{}", record));

        Map<TopicPartition, List<ConsumerRecord<String, String>>> recordMap = new HashMap<>();

        // TTL 过滤
        long now = System.currentTimeMillis();
        for (ConsumerRecord<String, String> record : records) {
            long timestamp = record.timestamp();
            if (now - timestamp < EXPIRE_INTERVAL) {
                TopicPartition topicPartition = new TopicPartition(record.topic(), record.partition());
                List<ConsumerRecord<String, String>> consumerRecords = recordMap.computeIfAbsent(topicPartition, k -> new ArrayList<>());
                consumerRecords.add(record);
            } else {
                log.warn("消息已过期：{}", record);
            }
        }

        return new ConsumerRecords<>(recordMap);
    }

    /**
     * 在 commit 之后调用
     */
    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        offsets.forEach((k, v) -> log.info("消费拦截器-提交：{} offset:{}", k, v.offset()));
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
