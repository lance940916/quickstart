package com.snailwu.design_pattern.c17_state_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:42 下午
 */
public class InActiveState implements State {
    @Override
    public void handle() {
        System.out.println("未激活");
    }
}
