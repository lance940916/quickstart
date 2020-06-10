package com.snailwu.design_pattern.c3_factory_method_pattern;

/**
 * 工厂方法模式
 * 抽象工厂角色、具体工厂角色、抽象产品角色、具体产品角色
 *
 * @author 吴庆龙
 * @date 2020/5/19 11:21 上午
 */
public class Main {

    public static void main(String[] args) {
        AbstractMobileFactory huaWeiFactory = new HuaWeiMobileFactory();
        AbstractMobile huaWei = huaWeiFactory.create();
        System.out.println(huaWei.brand());

        AbstractMobileFactory appleFactory = new AppleMobileFactory();
        AbstractMobile apple = appleFactory.create();
        System.out.println(apple.brand());
    }

}
