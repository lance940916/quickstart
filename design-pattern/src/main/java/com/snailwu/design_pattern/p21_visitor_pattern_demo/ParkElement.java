package com.snailwu.design_pattern.p21_visitor_pattern_demo;

/**
 * @author 吴庆龙
 * @date 2020/4/13 10:39 上午
 */
public class ParkElement implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
