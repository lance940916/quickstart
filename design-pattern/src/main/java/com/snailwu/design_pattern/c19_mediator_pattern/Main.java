package com.snailwu.design_pattern.c19_mediator_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 11:10 上午
 */
public class Main {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();

        Colleague cA = new ConcreteColleagueA();
        mediator.register(cA);
        Colleague cB = new ConcreteColleagueB();
        mediator.register(cB);

        cA.send();
        cB.send();
    }
}
