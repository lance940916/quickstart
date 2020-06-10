package com.snailwu.design_pattern.c23_interpreter_pattern;

/**
 * 抽象表达式
 *
 * @author 吴庆龙
 * @date 2020/5/19 4:30 下午
 */
public interface Expression {
    boolean interpret(String info);
}
