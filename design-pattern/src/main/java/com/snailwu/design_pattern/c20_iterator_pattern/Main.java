package com.snailwu.design_pattern.c20_iterator_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 10:58 上午
 */
public class Main {
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("1");
        aggregate.add("3");
        aggregate.add("5");

        Iterator iterator = aggregate.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
