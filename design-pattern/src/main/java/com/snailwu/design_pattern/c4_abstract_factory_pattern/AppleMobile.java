package com.snailwu.design_pattern.c4_abstract_factory_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 11:54 上午
 */
public class AppleMobile implements AbstractMobile {
    @Override
    public void name() {
        System.out.println("苹果手机");
    }
}
