package com.snailwu.design_pattern.c23_interpreter_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 4:32 下午
 */
public class AndExpression implements Expression {
    private final Expression city;
    private final Expression person;

    public AndExpression(Expression city, Expression person) {
        this.city = city;
        this.person = person;
    }

    @Override
    public boolean interpret(String info) {
        String[] array = info.split("的");
        return city.interpret(array[0]) && person.interpret(array[1]);
    }
}
