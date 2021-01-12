package com.snailwu.kafka.old;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author 吴庆龙
 * @date 2020/12/17 下午4:15
 */
public class Consumer10 {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-2");

        // 设置自动提交关闭
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(
//                "mike",
//                "tom",
                "jerry"
        ));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("ConsumerRecord(topic = " + record.topic()
                        + ", partition = " + record.partition()
                        + ", offset = " + record.offset()
                        + ", headers = " + record.headers()
                        + ", key = " + record.key()
                        + ", value = " + record.value() + ")");
            }

            consumer.commitSync();
            System.out.println("手动Commit");
        }

    }

}
