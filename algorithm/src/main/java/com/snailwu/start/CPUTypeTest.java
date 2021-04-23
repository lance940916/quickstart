package com.snailwu.start;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wu
 */
public class CPUTypeTest implements Runnable {

    /**
     * 整体执行时间，包括在队列中等待的时间
     */
    List<Long> wholeTimeList;
    /**
     * 真正执行时间
     */
    List<Long> runTimeList;

    private long initStartTime;

    /**
     * 构造函数
     */
    public CPUTypeTest(List<Long> runTimeList, List<Long> wholeTimeList) {
        initStartTime = System.currentTimeMillis();
        this.runTimeList = runTimeList;
        this.wholeTimeList = wholeTimeList;
    }

    /**
     * 判断素数
     */
    public boolean isPrime(final int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 計算素数
     */
    public int countPrimes(final int lower, final int upper) {
        int total = 0;
        for (int i = lower; i <= upper; i++) {
            if (isPrime(i)) {
                total++;
            }
        }
        return total;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        int countPrimes = countPrimes(1, 1000000);
        long end = System.currentTimeMillis();

        long wholeTime = end - initStartTime;
        long runTime = end - start;
        wholeTimeList.add(wholeTime);
        runTimeList.add(runTime);
        System.out.println("单个线程花费时间：" + (end - start));
    }

    public static void main(String[] args) throws InterruptedException {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        // 8
        System.out.println(availableProcessors);
        int threadCount = 9;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                threadCount, threadCount, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                Executors.defaultThreadFactory()
        );
        executor.prestartAllCoreThreads();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            List<Long> wholeTimeList = new ArrayList<>();
            List<Long> runTimeList = new ArrayList<>();
            executor.submit(new CPUTypeTest(runTimeList, wholeTimeList));
        }

        while (executor.getActiveCount() > 0) {
//            System.out.println("线程数：" + executor.getPoolSize());
//            TimeUnit.SECONDS.sleep(1);
        }
        long end = System.currentTimeMillis();
        // 6 = 5732
        // 4 = 6310
        // 8 = 5257
        // 9 = 5174
        // 10 = 5271
        // 12 = 5232
        System.out.println("总时间：" + (end - start));

        executor.shutdown();

    }

}