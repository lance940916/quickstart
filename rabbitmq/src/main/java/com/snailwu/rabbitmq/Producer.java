package com.snailwu.rabbitmq;

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

//        new AMQP.BasicProperties().builder()

        for (int i = 0; i < 5; i++) {
            String message = "Hello RabbitMQ!";
            channel.basicPublish("bus", "bus.cpu", null,
                    message.getBytes(StandardCharsets.UTF_8));

            System.out.println("Producer Send '" + message + "'");
        }

        channel.close();
        connection.close();
    }

}
