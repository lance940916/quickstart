package com.snailwu.design_pattern.c1_single_pattern;

/**
 * 使用一个 if 判断实例是否被实例化，存在多线程问题
 * 如果执行 getInstance 方法时，只有一个线程执行，没问题，如果多个线程同时进入 getInstance 方法，则有问题。
 *
 * @author 吴庆龙
 * @date 2020/5/19 10:29 上午
 */
public class SimpleSingle {

    public static SimpleSingle instance;

    private SimpleSingle(){}

    public static SimpleSingle getInstance() {
        if (instance == null) {
            instance = new SimpleSingle();
        }
        return instance;
    }
}
