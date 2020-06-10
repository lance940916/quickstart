package com.snailwu.design_pattern.c21_visitor_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 5:29 下午
 */
public interface Element {
    void accept(Visitor visitor);
}
