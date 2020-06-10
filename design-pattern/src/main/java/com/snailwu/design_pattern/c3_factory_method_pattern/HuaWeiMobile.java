package com.snailwu.design_pattern.c3_factory_method_pattern;

/**
 * 具体产品角色
 *
 * @author 吴庆龙
 * @date 2020/5/19 11:24 上午
 */
public class HuaWeiMobile implements AbstractMobile {
    @Override
    public String brand() {
        return "HuaWei";
    }
}
