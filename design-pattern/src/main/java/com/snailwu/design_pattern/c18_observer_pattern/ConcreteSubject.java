package com.snailwu.design_pattern.c18_observer_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:30 下午
 */
public class ConcreteSubject extends Subject {
    @Override
    public void notice() {
        System.out.println("通知所有观察者");
        for (Observer observer : list) {
            observer.response();
        }
    }
}
