package com.snailwu.rabbitmq.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 * @author wu
 */
public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明一个死信队列的Exchange和Queue
        channel.exchangeDeclare("dlx.exchange001", "topic", true, false, false, null);
        channel.queueDeclare("dlx.queue001", true, false, false, null);
        channel.queueBind("dlx.queue001", "dlx.exchange001", "#");

        // 死信队列的参数
        Map<String, Object> queueArguments = new HashMap<>();
        // 指定死信队列的队列名
        queueArguments.put("x-dead-letter-exchange", "dlx.exchange001");

        // 声明一个正常的Exchange和Queue
        channel.exchangeDeclare("bus", "direct", true, false, false, null);
        channel.queueDeclare("cpu", true, false, false, queueArguments);
        channel.queueBind("cpu", "bus", "bus.cpu");


//        // 消费正常的队列
//        channel.basicConsume("cpu", false, new WuConsumer(channel));
//        // 消费死信队列
//        channel.basicConsume("cpu", true, new DLXConsumer(channel));


    }

}
