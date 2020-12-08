package com.snailwu.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

        // 声明一个Exchange
        channel.exchangeDeclare("bus", "direct", true, false, false, null);

        // 声明一个Queue
        channel.queueDeclare("cpu", true, false, false, null);

        // 建立Exchange和Queue的绑定关系
        channel.queueBind("cpu", "bus", "bus.cpu");

        // 创建消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 设置Channel
        channel.basicConsume("cpu", true, consumer);

        // 获取消息
        System.out.println("Waiting for messages. To exit press CTRL+C");
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Customer Rece '" + message + "'");
        }

    }

}
