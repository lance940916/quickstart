package com.snailwu.design_pattern.c18_observer_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:28 下午
 */
public class ObserverB implements Observer {
    @Override
    public void response() {
        System.out.println("观察者 B 的响应信息");
    }
}
