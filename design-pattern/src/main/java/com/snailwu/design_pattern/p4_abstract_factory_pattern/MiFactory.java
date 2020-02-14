package com.snailwu.design_pattern.p4_abstract_factory_pattern;

/**
 * @author: 吴庆龙
 * @date: 2020/1/6 10:31 下午
 */
public class MiFactory extends AbstractFactory {
    @Override
    public AbstractTV createTV() {
        return new MiTV();
    }

    @Override
    public AbstractMobile createMobile() {
        return new MiMobile();
    }
}
