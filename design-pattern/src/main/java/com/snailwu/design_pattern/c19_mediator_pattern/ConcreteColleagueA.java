package com.snailwu.design_pattern.c19_mediator_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:19 下午
 */
public class ConcreteColleagueA extends Colleague {
    @Override
    public void receive() {
        System.out.println("同事类 A 收到消息");
    }

    @Override
    public void send() {
        System.out.println("同事类 A 广播消息");
        mediator.replay(this);
    }
}
