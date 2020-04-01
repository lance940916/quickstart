package com.snailwu.design_pattern.p19_state_pattern;

/**
 * @author 吴庆龙
 * @date 2020/4/1 10:17 上午
 */
public abstract class Colleague {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive();
    public abstract void send();
}
