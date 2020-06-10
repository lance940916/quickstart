package com.snailwu.design_pattern.c2_prototype_pattern;

/**
 * 原型模式，基于一个基础对象克隆出一个新对象
 *
 * @author 吴庆龙
 * @date 2020/5/19 11:15 上午
 */
public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        User mike = new User();
        mike.setName("Mike");
        mike.setAge("10");

        User tom = mike.clone();
        tom.setName("Tom");

        System.out.println(mike);
        System.out.println(tom);

    }

}
