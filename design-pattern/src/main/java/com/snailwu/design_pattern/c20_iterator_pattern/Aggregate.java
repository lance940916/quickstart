package com.snailwu.design_pattern.c20_iterator_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/20 11:05 上午
 */
public interface Aggregate {
    void add(String obj);

    void remove(String obj);

    Iterator getIterator();
}
