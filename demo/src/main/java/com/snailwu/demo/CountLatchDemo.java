package com.snailwu.demo;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author 吴庆龙
 * @date 2020/6/11 5:28 下午
 */
public class CountLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int threadNum = 20;
        CountDownLatch threadLatch = new CountDownLatch(threadNum);
        CountDownLatch mainLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    threadLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String time = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.sss");
                System.out.println("时间: " + time);
                mainLatch.countDown();
            }).start();
            threadLatch.countDown();
        }
        mainLatch.await();
        System.out.println("结束");
    }
}
