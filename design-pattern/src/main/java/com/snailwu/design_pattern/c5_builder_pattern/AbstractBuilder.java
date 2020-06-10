package com.snailwu.design_pattern.c5_builder_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 4:21 下午
 */
public abstract class AbstractBuilder {
    protected User user = new User();

    public abstract void buildName();

    public abstract void buildAge();

    public User getResult() {
        return user;
    }
}
