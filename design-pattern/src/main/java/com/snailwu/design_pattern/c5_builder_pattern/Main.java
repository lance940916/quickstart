package com.snailwu.design_pattern.c5_builder_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 4:24 下午
 */
public class Main {
    public static void main(String[] args) {
        Director director = new Director(new MikeBuilder());
        User mike = director.construct();
        System.out.println(mike);
    }
}
