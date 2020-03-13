package com.snailwu.design_pattern.p14_strategy_pattern;

/**
 * @author: 吴庆龙
 * @date: 2020/3/9 4:19 下午
 */
public class ConcreteStrategyB implements Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("具体策略模式 B");
    }
}
