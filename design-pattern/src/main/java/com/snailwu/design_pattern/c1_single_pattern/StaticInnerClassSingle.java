package com.snailwu.design_pattern.c1_single_pattern;

/**
 * 静态内部类实现的单例模式
 * 这里在使用 SingleClass 时才回去加载这个类，因为一个 ClassLoader 只能允许类加载一次，所以这里又 JVM 进行加锁控制。
 *
 * @author 吴庆龙
 * @date 2020/5/19 11:10 上午
 */
public class StaticInnerClassSingle {

    private StaticInnerClassSingle(){}

    public synchronized static SingleClass getInstance() {
        return SingleClass.instance;
    }

    private static class SingleClass {
        private static final SingleClass instance = new SingleClass();
    }

}
