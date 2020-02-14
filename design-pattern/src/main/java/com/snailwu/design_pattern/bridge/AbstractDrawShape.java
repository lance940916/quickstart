package com.snailwu.design_pattern.bridge;

/**
 * @author: 吴庆龙
 * @date: 2020/1/19 5:13 下午
 */
public abstract class AbstractDrawShape {

    protected IColor color;

    public AbstractDrawShape(IColor color) {
        this.color = color;
    }

    public abstract void draw();

}
