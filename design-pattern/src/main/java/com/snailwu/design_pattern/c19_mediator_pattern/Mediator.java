package com.snailwu.design_pattern.c19_mediator_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:13 下午
 */
public interface Mediator {

    void register(Colleague colleague);

    void replay(Colleague colleague);

}
