package com.snailwu.design_pattern.c4_abstract_factory_pattern;

/**
 * 抽象工厂
 *
 * @author 吴庆龙
 * @date 2020/5/19 11:43 上午
 */
public interface AbstractFactory {

    AbstractTV tv();

    AbstractMobile mobile();

}