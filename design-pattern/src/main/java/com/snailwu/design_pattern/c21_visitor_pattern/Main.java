package com.snailwu.design_pattern.c21_visitor_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 10:51 上午
 */
public class Main {
    public static void main(String[] args) {
        ZooElement zoo = new ZooElement();
        ParkElement park = new ParkElement();

        Visitor mike = new MikeVisitor();
        mike.visit(zoo);
        mike.visit(park);


    }
}
