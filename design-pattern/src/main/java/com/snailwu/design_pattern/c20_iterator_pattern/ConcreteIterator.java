package com.snailwu.design_pattern.c20_iterator_pattern;

import java.util.List;

/**
 * @author 吴庆龙
 * @date 2020/5/20 11:02 上午
 */
public class ConcreteIterator implements Iterator {

    private final List<String> list;
    private int index = -1;

    public ConcreteIterator(List<String> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        if (list == null || list.isEmpty()) {
            return false;
        }
        return index < list.size() - 1;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            return list.get(++index);
        }
        return null;
    }
}
