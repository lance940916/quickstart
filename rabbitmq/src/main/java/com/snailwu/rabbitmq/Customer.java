package com.snailwu.rabbitmq;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 * @author wu
 */
public class Customer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Customer.class);

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println("Waiting for messages. To exit press CTRL+C");

        // =========== 使用自定义的Consumer BEGIN
        channel.basicQos(2);
        Consumer consumer = new WuConsumer(channel);
        channel.basicConsume("wu.mike", false, "java-client", consumer);
        // =========== 使用自定义的Consumer END

//        channel.basicQos(5);
//        channel.basicConsume("wu.mike", true, "wu-client", new DeliverCallback() {
//            @Override
//            public void handle(String consumerTag, Delivery message) throws IOException {
//                Envelope envelope = message.getEnvelope();
//                LOGGER.warn("consumerTag:[{}] Envelope:[{}] Message:[{}]", consumerTag, envelope,
//                        new String(message.getBody(), StandardCharsets.UTF_8));
//            }
//        }, new CancelCallback() {
//            @Override
//            public void handle(String consumerTag) throws IOException {
//                LOGGER.warn("consumerTag: [{}]", consumerTag);
//            }
//        });

    }

}
