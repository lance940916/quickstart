package com.snailwu.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;

import java.io.IOException;
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

        // Channel级别的消费限流
//        channel.basicQos(2);

        System.out.println("Waiting for messages. To exit press CTRL+C");

        // =========== 使用RabbitMQ自己的Consumer BEGIN


        // =========== 使用自定义的Consumer BEGIN
        Consumer consumer = new WuConsumer(channel);
        channel.basicConsume("cpu", true, consumer);
        // =========== 使用自定义的Consumer END




    }

}
