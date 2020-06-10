package com.snailwu.design_pattern.c18_observer_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:30 下午
 */
public class Main {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        subject.add(new ObserverA());
        subject.add(new ObserverB());

        subject.notice();
    }
}
