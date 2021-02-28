package com.snailwu.quickstart.kafka.old;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author 吴庆龙
 * @date 2020/12/16 下午6:41
 */
public class Producer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // 自定义分区策略
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.snailwu.kafka.MyPartitioner");

        // acks=0：如果设置为0，生产者不会等待kafka的响应。
        // acks=1：这个配置意味着kafka会把这条消息写到本地日志文件中，但是不会等待集群中其他机器的成功响应。
        // acks=all：这个配置意味着leader会等待所有的follower同步完成。这个确保消息不会丢失，除非kafka集群中所有机器挂掉。这是最强的可用性保证。
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        // 配置为大于0的值的话，客户端会在消息发送失败时重新发送。
        props.put(ProducerConfig.RETRIES_CONFIG, "1");

        // 每当将多个记录发送到同一分区时，生产者将尝试将记录一起批处理成更少的请求。这有助于提高客户端和服务器的性能。
        // 0 禁用，这里是指消息的 bytes 大写，不是多少条消息
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, "0");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>("jerry", "name_info", "I amd Mike.");
        // 等待发送完成
        producer.send(record).get();

        producer.close();
    }

}
