package com.snailwu.quickstart.redis;

import org.apache.commons.lang3.time.DateFormatUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 * @author wu
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(5);

        // 哨兵信息
        Set<String> sentinels = new HashSet<>(Arrays.asList(
                "127.0.0.1:26379",
                "127.0.0.1:26380"
        ));

        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels,
                jedisPoolConfig);
        Jedis jedis = sentinelPool.getResource();
        jedis.set("age", "20");

        while (true) {
            String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            jedis = sentinelPool.getResource();
            System.out.println( format + " => " + jedis.get("age"));
            Thread.sleep(1000);
        }

    }
}
