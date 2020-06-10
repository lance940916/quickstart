package com.snailwu.design_pattern.c19_mediator_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:19 下午
 */
public class ConcreteColleagueB extends Colleague {
    @Override
    public void receive() {
        System.out.println("同事类 B 收到消息");
    }

    @Override
    public void send() {
        System.out.println("同事类 B 广播消息");
        mediator.replay(this);
    }
}
