package com.snailwu.design_pattern.c19_mediator_pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:17 下午
 */
@Getter
@Setter
public abstract class Colleague {
    private String name;
    protected Mediator mediator;

    public abstract void receive();

    public abstract void send();
}
