package com.snailwu.design_pattern.c4_abstract_factory_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 11:56 上午
 */
public class HuaWeiFactory implements AbstractFactory {
    @Override
    public AbstractTV tv() {
        return new HuaWeiTV();
    }

    @Override
    public AbstractMobile mobile() {
        return new HuaWeiMobile();
    }
}
