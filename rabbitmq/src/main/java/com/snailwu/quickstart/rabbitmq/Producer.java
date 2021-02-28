package com.snailwu.quickstart.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
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

//        // 启用消息确认模式
//        channel.confirmSelect();
//
//        // 添加一个确认监听（先监听，后发送消息）
//        channel.addConfirmListener(new ConfirmListener() {
//            /**
//             * ACK 确认
//             * @param deliveryTag 消息的唯一标签
//             * @param multiple 是否批量
//             * @throws IOException ERROR
//             */
//            @Override
//            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
//                System.out.println("ConfirmListener ACK! deliveryTag:" + deliveryTag);
//            }
//
//            @Override
//            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
//                System.out.println("ConfirmListener NACK! deliveryTag:" + deliveryTag);
//            }
//        });

        // 添加不可达消息监听
//        channel.addReturnListener(new ReturnListener() {
//            @Override
//            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
//                                     AMQP.BasicProperties properties, byte[] body) throws IOException {
//                System.out.println("ReturnListener Exchange: " + exchange + ", RoutingKey: " + routingKey);
//            }
//        });

        // 发送消息
        for (int i = 0; i < 10; i++) {
            String message = "Hello RabbitMQ! Index:" + i;
            channel.basicPublish("wu.users", "wu.tom", null,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Producer Send '" + message + "'");
        }

        // 测试 mandatory 属性
//        channel.basicPublish("bus", "bus.cpu111", true, null,
//                "Hello ReturnListener".getBytes(StandardCharsets.UTF_8));
//        System.out.println("Producer Send 'Hello ReturnListener'");


        channel.close();
        connection.close();

    }

}
