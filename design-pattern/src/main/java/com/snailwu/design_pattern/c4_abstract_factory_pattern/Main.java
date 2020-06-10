package com.snailwu.design_pattern.c4_abstract_factory_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 11:57 上午
 */
public class Main {
    public static void main(String[] args) {
        AbstractFactory hw = new HuaWeiFactory();
        hw.tv().name();
        hw.mobile().name();

        AbstractFactory apple = new AppleFactory();
        apple.tv().name();
        apple.mobile().name();
    }
}
