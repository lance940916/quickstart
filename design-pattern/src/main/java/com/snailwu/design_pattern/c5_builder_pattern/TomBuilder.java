package com.snailwu.design_pattern.c5_builder_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 4:20 下午
 */
public class TomBuilder extends AbstractBuilder {
    @Override
    public void buildName() {
        user.setName("Tom");
    }

    @Override
    public void buildAge() {
        user.setAge(25);
    }
}
