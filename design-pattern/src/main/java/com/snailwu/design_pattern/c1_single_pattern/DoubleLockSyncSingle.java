package com.snailwu.design_pattern.c1_single_pattern;

/**
 * 双重锁 + sync 检查机制
 * 既集成了 if 判断的简单性，又集成了 sync 的可靠性
 * 但是有一个问题，就是实例化对象这个操作并不是原子性操作，所以可能会因为重排序导致可见性问题：在退出 sync 结构体后，对象还没有被初始化。
 *
 * @author 吴庆龙
 * @date 2020/5/19 11:05 上午
 */
public class DoubleLockSyncSingle {

    public static DoubleLockSyncSingle instance;

    private DoubleLockSyncSingle(){}

    public static DoubleLockSyncSingle getInstance() {
        if (instance == null) {
            synchronized (DoubleLockSyncSingle.class) {
                if (instance == null) {
                    instance = new DoubleLockSyncSingle();
                }
            }
        }
        return instance;
    }

}
