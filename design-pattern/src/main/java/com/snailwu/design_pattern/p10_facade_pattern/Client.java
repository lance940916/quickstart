package com.snailwu.design_pattern.p10_facade_pattern;

/**
 * @author: 吴庆龙
 * @date: 2020/3/2 5:03 下午
 */
public class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.f();
    }
}
