package com.snailwu.design_pattern.c21_visitor_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 5:32 下午
 */
public class TomVisitor implements Visitor {
    @Override
    public void visit(ZooElement zooElement) {
        System.out.println("Tom 访问动物园, 评价:很多人乱喂动物");
    }

    @Override
    public void visit(ParkElement parkElement) {
        System.out.println("Tom 访问公园, 评价:空气很好");
    }
}
