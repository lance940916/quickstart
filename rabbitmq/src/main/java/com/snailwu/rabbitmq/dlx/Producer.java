package com.snailwu.rabbitmq.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 * @author wu
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 发送消息
        for (int i = 1; i <= 5; i++) {
            String message = "RabbitMQ DLX 消息 Index:" + i;

            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties.Builder()
                    .expiration("10000")
                    .build();
            channel.basicPublish("bus", "bus.cpu", basicProperties,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Producer Send '" + message + "'");
        }

        channel.close();
        connection.close();
    }

}
