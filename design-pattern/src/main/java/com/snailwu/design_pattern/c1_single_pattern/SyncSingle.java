package com.snailwu.design_pattern.c1_single_pattern;

/**
 * 使用 synchronized 进行控制线程的并发，由 JVM 提供支持，毫无安全问题。
 * 但是，有一个性能问题就是每次获取实例都需要进行加锁解锁。
 *
 * @author 吴庆龙
 * @date 2020/5/19 10:34 上午
 */
public class SyncSingle {

    public static SyncSingle instance;

    private SyncSingle(){}

    public synchronized static SyncSingle getInstance() {
        if (instance == null) {
            instance = new SyncSingle();
        }
        return instance;
    }

}
