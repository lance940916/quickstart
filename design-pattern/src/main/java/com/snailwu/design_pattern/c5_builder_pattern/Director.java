package com.snailwu.design_pattern.c5_builder_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 4:25 下午
 */
public class Director {

    private final AbstractBuilder builder;

    public Director(AbstractBuilder builder) {
        this.builder = builder;
    }

    public User construct() {
        builder.buildName();
        builder.buildAge();
        return builder.getResult();
    }
}
