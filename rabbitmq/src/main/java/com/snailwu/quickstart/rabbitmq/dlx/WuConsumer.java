package com.snailwu.quickstart.rabbitmq.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 吴庆龙
 * @date 2020/12/9 上午11:47
 */
public class WuConsumer extends DefaultConsumer {

    private final Channel channel;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public WuConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String exchange = envelope.getExchange();
        String routingKey = envelope.getRoutingKey();
        long deliveryTag = envelope.getDeliveryTag();
        String message = new String(body, StandardCharsets.UTF_8);
        System.out.println("WuConsumer 收到消息 deliveryTag:" + deliveryTag + ", exchange:" + exchange
                + ", routingKey:" + routingKey + ", message:" + message);

        channel.basicNack(deliveryTag, false, true);
    }
}
