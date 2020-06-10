package com.snailwu.design_pattern.c17_state_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:42 下午
 */
public class Main {
    public static void main(String[] args) {
        Context context = new Context(new InActiveState());
        context.handle();

        context.setState(new ActiveState());
        context.handle();
    }
}
