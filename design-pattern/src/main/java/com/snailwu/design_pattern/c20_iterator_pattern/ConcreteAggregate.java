package com.snailwu.design_pattern.c20_iterator_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴庆龙
 * @date 2020/5/20 11:05 上午
 */
public class ConcreteAggregate implements Aggregate {
    private final List<String> list = new ArrayList<>();

    @Override
    public void add(String obj) {
        list.add(obj);
    }

    @Override
    public void remove(String obj) {
        list.remove(obj);
    }

    @Override
    public Iterator getIterator() {
        return new ConcreteIterator(list);
    }
}
