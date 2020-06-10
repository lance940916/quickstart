package com.snailwu.design_pattern.c23_interpreter_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴庆龙
 * @date 2020/5/19 4:31 下午
 */
public class TerminalExpression implements Expression {

    private List<String> list = new ArrayList<>();

    public TerminalExpression(List<String> list) {
        this.list = list;
    }

    @Override
    public boolean interpret(String info) {
        return list.contains(info);
    }
}
