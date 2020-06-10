package com.snailwu.design_pattern.c23_interpreter_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 4:37 下午
 */
public class Main {
    public static void main(String[] args) {
        Context bus = new Context();
        bus.freeRide("北京的儿童");
        bus.freeRide("北京的老人");
        bus.freeRide("北京的学生");
        bus.freeRide("广州的老人");
        bus.freeRide("广州的儿童");
    }
}
