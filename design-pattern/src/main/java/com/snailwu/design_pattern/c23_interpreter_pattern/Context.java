package com.snailwu.design_pattern.c23_interpreter_pattern;

import java.util.Arrays;
import java.util.List;

/**
 * @author 吴庆龙
 * @date 2020/5/19 4:34 下午
 */
public class Context {

    private List<String> cityList = Arrays.asList("北京");
    private List<String> personList = Arrays.asList("儿童", "老人");
    private Expression andExpression;

    public Context() {
        Expression city = new TerminalExpression(cityList);
        Expression person = new TerminalExpression(personList);
        andExpression = new AndExpression(city, person);
    }

    public void freeRide(String info) {
        if (andExpression.interpret(info)) {
            System.out.println("您是" + info + "，您本次乘车免费！");
        } else {
            System.out.println("您是" + info + "，您不是免费人员.");
        }
    }
}
