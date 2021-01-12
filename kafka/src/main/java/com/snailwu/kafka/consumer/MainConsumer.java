package com.snailwu.kafka.consumer;

import com.snailwu.kafka.consumer.interceptor.ReceiveInterceptor;
import com.snailwu.kafka.producer.interceptor.SendLogInterceptor;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;

/**
 * @author WuQinglong
 * @date 2021/1/5 09:56
 */
public class MainConsumer {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    public static void main(String[] args) {
        new MainConsumer().start();
    }

    private void start() {
        Properties props = new Properties();
        // 必填参数
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9091,127.0.0.1:9092,127.0.0.1:9093");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "Group-001");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // 可选参数
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 3000); // 默认 5秒
//        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "3000");
//        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // 在一次拉取请求中最小的拉取数据量，默认是 1B
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1);
        // 最大拉取的数据量，默认是 50MB。注意：大于50MB的消息也能消费
        // Kafka所能接收的消息的大小是通过服务端参数 message.max.bytes 来设置的
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 52428800);
        // 如果没有足够的消息满足 fetch_min_bytes 的要求，等待 500ms 返回。
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 500);
        // 配置每个分区里返回给 Consumer 的最大数据量，默认是 1MB。
        // 这个参数是用来现在一次拉取中每个分区的消息大小
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 1048576);
        // 配置每次拉取消息的最大数量
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
        // 配置多久之后关闭闲置的连接
        props.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 540000);
        // 配置是否可以向消费者公开。如果是true，则只能使用 subscribe(Collection) 订阅，而不能使用 subscribe(Pattern) 的方式订阅
        props.put(ConsumerConfig.EXCLUDE_INTERNAL_TOPICS_CONFIG, "true"); // 默认是 true
        // 配置 Consumer 等待请求响应的最长时间
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        // 配置 SOCKET 接收消息的缓冲区（SO_RECBUF）
        props.put(ConsumerConfig.RECEIVE_BUFFER_CONFIG, 65536);
        // 配置 SOCKET 发送消息的缓冲区（SO_SNDBUF）
        props.put(ConsumerConfig.SEND_BUFFER_CONFIG, 131072);
        // 配置元数据的过期时间
        props.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, 30000);
        // 配置尝试重连指定主机之前的等待时间
        props.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, 50);
        // 配置尝试重新发送失败的请求待指定的主题分区之前的等待时间
        props.put(ConsumerConfig.RETRY_BACKOFF_MS_CONFIG, 100);
        // 配置消费者事务的隔离级别，有效值：read_uncommitted 和 read_committed
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, ConsumerConfig.DEFAULT_ISOLATION_LEVEL);

        // 配置拦截器
        String customInterceptors = ReceiveInterceptor.class.getName();
        props.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, customInterceptors);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // 查询主题对应的分区信息
//        List<PartitionInfo> smsPartitionInfo = consumer.partitionsFor("sms");
//        for (PartitionInfo partitionInfo : smsPartitionInfo) {
//            log.info("分区信息：{}", partitionInfo);
//        }

        // 通过 subscribe 订阅主题具有消费者自动再均衡的功能
//        List<String> topics = Arrays.asList("sms", "log");
//        consumer.subscribe(topics);
        consumer.subscribe(Arrays.asList("user"));

        // 带有超时时间的拉取，设置为 0 时，立即返回，不管是否拉取到消息
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));

        // 按照分区维度进行消费
        Set<TopicPartition> partitions = records.partitions();
        for (TopicPartition partition : partitions) {
            log.info("消费 {} 分区的消息", partition);
            records.records(partition).forEach(record -> log.info("消费消息：{}", record));
        }
        consumer.close();

        // 按照主题维度进行消费
//        for (String topic : topics) {
//            log.info("消费 {} 主题的消息", topic);
//            Iterable<ConsumerRecord<String, String>> topicIter = records.records(topic);
//            topicIter.forEach(record -> log.info("消费消息：{}", record));
//        }

        // 获取消费位移
//        TopicPartition sms0 = new TopicPartition("sms", 0);
//        consumer.assign(Collections.singletonList(sms0));
//        // 必须使用 assign 进行消费指定分区，才能调动 position 方法
//        // 获取下一次拉取的 offset
//        long position = consumer.position(sms0);
//        log.info("Topic:{} position:{}", sms0, position);
//        Map<TopicPartition, OffsetAndMetadata> committed = consumer.committed(Collections.singleton(sms0));
//        committed.forEach((k, v) -> log.info("Topic:{} offset:{}", k, v.offset()));

        // 精确到 Partition 的位移提交
//        for (ConsumerRecord<String, String> record : records) {
//            long offset = record.offset();
//            TopicPartition partition = new TopicPartition(record.topic(), record.partition());
//            consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(offset + 1)));
//        }

//        consumer.wakeup();

        // 指定位移进行消费
//        List<TopicPartition> partitions = Collections.singletonList(new TopicPartition("user", 0));
//        consumer.assign(partitions);
//        consumer.seekToBeginning(partitions);
//        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
//        records.forEach(record -> log.info("消费User：{}", record));
//        consumer.commitSync();

        // 再均衡监听器，当这个消费者组有新的消费者加入后，会进行在均衡操作
        // 1. MainConsumer 启动后消费 sms-1 sms-2 sms-0
        // 2. MikeConsumer 启动后，发生再均衡，这时 MainConsumer 被分配去消费 sms-2，MikeConsumer 被分配去消费 sms-1 sms-0
//        consumer.subscribe(Arrays.asList("sms"), new ConsumerRebalanceListener() {
//            @Override
//            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
//                // 在再均衡之前和消费者停读取消息之后被调用
//
//                // 保存 offset 到 数据库
//
//                // log
//                partitions.forEach(item -> log.info("再均衡之前：{}", item));
//            }
//
//            @Override
//            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
//                // 在重新分配分区之后和消费者开始读取消息之前被调用
//
//                // 从数据库加载 offset，然后使用 seek 进行指定位移
//
//                // log
//                partitions.forEach(item -> log.info("再均衡之后：{}", item));
//            }
//        });
//
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
//            records.forEach(record -> log.info("消费：{}", record));
//        }

    }

}
