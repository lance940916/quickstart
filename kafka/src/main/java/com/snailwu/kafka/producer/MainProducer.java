package com.snailwu.kafka.producer;

import com.snailwu.kafka.producer.interceptor.SendLog2Interceptor;
import com.snailwu.kafka.producer.interceptor.SendLogInterceptor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author WuQinglong
 * @date 2021/1/5 09:44
 */
public class MainProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 配置生产者参数
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9091,127.0.0.1:9092,127.0.0.1:9093");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "main-producer");
        // 如果发生可重试异常，则进行重试
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        // 设置每次重试的时间间隔
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 3000);
        // 默认值是 5，设置每个连接最多只能缓存 5 个未响应的请求，超过该数值之后就不能再向这个连接发送更多的请求了，除非有缓存的请求收到了响应
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);
        // 默认的分区器，key 非 null 时，会进行计算得到分区号；key 为 null 时，会任意选择一个可用分区号。
        // 参考 org.apache.kafka.clients.producer.internals.StickyPartitionCache.nextPartition
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, DefaultPartitioner.class);
        // 配置生产者拦截器，是有顺序的
        String customInterceptors = SendLogInterceptor.class.getName()/* + "," + SendLog2Interceptor.class.getName()*/;
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, customInterceptors);
        // 消息的缓存大小，以便 Sender 线程可以批量发送
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 335554432); // 默认 32MB
        // 当消息生产速度大于消息发送速度时，会导致缓存大小不足，这时候 send() 方法要么堵塞要么抛出异常，取决于 MAX_BLOCK_MS_CONFIG 配置
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 60000); // 默认 60秒
        //
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); // 默认 16MB
        // 元数据更新间隔时间
        props.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, 300000);
        // acks=1：生产者发送消息后，只要分区的 leader 副本成功写入消息，就收到服务端的成功响应
        // acks=0：生产者发送消息后不需要等待任务服务端的响应
        // acks=-1：生产者发送消息后需要等待 ISR 中的所有副本都成功写入消息之后才会收到服务端的响应
        props.put(ProducerConfig.ACKS_CONFIG, "-1");
        // 限制生产者客户端所能发送的消息的最大值
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 1048576); // 默认值 1M
        // 指定在多久之后关闭空置的连接，默认是 9 分钟
        props.put(ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 540000);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

//        ProducerRecord<String, String> record = new ProducerRecord<>("sms", "009", "收款900万元");
//        producer.send(record).get();

        // 发送到指定的分区
//        ProducerRecord<String, String> record0 = new ProducerRecord<>("sms", 0, "001", "收款100万元");
//        ProducerRecord<String, String> record1 = new ProducerRecord<>("sms", 1, "002", "收款200万元");
//        ProducerRecord<String, String> record2 = new ProducerRecord<>("sms", 2, "003", "收款300万元");
//        ProducerRecord<String, String> record00 = new ProducerRecord<>("sms", 0, "004", "收款400万元");
//        producer.send(record0).get();
//        producer.send(record1).get();
//        producer.send(record2).get();
//        producer.send(record00).get();

//        producer.send(new ProducerRecord<>("sms", "001", "收款100万元")).get();
//        producer.send(new ProducerRecord<>("sms", "007", "收款700万元")).get();
//        producer.send(new ProducerRecord<>("log", "003", "收款300万元")).get();
//        producer.send(new ProducerRecord<>("log", "004", "收款400万元")).get();
//        producer.send(new ProducerRecord<>("log", "002", "收款200万元")).get();

        while (true) {
            producer.send(new ProducerRecord<>("user", 0, System.currentTimeMillis(),
                    "u-001", "Mike-001")).get();

            TimeUnit.SECONDS.sleep(1);
        }

//        producer.close();
    }

}
