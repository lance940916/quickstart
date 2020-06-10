package com.snailwu.design_pattern.c3_factory_method_pattern;

/**
 * 具体工厂角色
 *
 * @author 吴庆龙
 * @date 2020/5/19 11:25 上午
 */
public class HuaWeiMobileFactory implements AbstractMobileFactory {
    @Override
    public AbstractMobile create() {
        return new HuaWeiMobile();
    }
}
