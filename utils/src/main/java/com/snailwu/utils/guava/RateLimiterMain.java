package com.snailwu.utils.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 吴庆龙
 * @date 2020/10/20 4:52 下午
 */
public class RateLimiterMain {

    public static void main(String[] args) {

//        RateLimiter rateLimiter = RateLimiter.create(1.5);
        RateLimiter rateLimiter = RateLimiter.create(10, 1000, TimeUnit.MILLISECONDS);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            rateLimiter.acquire();
            executorService.submit(() -> {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
                System.out.println("时间:" + format.format(new Date()) + "线程:" + Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
    }

}
