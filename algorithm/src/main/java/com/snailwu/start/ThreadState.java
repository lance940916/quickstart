package com.snailwu.start;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author WuQinglong
 * @date 2021/3/10 14:37
 */
public class ThreadState {

    private static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                try {
                    synchronized (list) {
                        while (list.size() == 10) {
                            System.out.println("队列已满");
                            list.wait();
                        }
                        int i = RandomUtils.nextInt();
                        list.add(i);
                        System.out.println("生产元素:" + i);
                        list.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者1").start();

        new Thread(() -> {
            while (true) {
                try {
                    synchronized (list) {
                        while (list.isEmpty()) {
                            System.out.println("队列是空");
                            list.wait();
                        }
                        Integer pop = list.pop();
                        System.out.println("消费元素:" + pop);
                        list.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者1").start();


    }

}
