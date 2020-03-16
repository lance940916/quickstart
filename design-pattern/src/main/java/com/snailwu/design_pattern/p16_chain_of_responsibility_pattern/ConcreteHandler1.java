package com.snailwu.design_pattern.p16_chain_of_responsibility_pattern;

/**
 * @author: 吴庆龙
 * @date: 2020/3/16 10:15 上午
 */
public class ConcreteHandler1 extends Handler {
    @Override
    public void handleRequest(String request) {
        if ("one".equals(request)) {
            System.out.println("具体处理者 1 负责该请求的处理");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            } else {
                System.out.println("没有人处理该请求，结束。");
            }
        }
    }
}
