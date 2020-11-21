package com.snailwu.utils.guava;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author 吴庆龙
 * @date 2020/10/20 5:08 下午
 */
public class SamephoreMain {
    public static void main(String[] args) {
        new SamephoreMain().start();
    }

    private void start() {
        int num = 8; // 工人数
        Semaphore semaphore = new Semaphore(5); // 机器数目

        for (int i = 0; i < num; i++) {
            new Thread(new RunImpl(i, semaphore)).start();
        }

    }

    class RunImpl implements Runnable {

        private int num;
        private Semaphore semaphore;
        public RunImpl(int num, Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人" + this.num + "占用一个机器在生产...");
                Thread.sleep(new Random().nextInt(2000)); // 以睡眠来模拟写入数据操作

                System.out.println("工人" + this.num + "释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
