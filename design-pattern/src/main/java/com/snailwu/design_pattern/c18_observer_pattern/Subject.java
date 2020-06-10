package com.snailwu.design_pattern.c18_observer_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:28 下午
 */
public abstract class Subject {

    protected final List<Observer> list = new ArrayList<>();

    public void add(Observer observer) {
        list.add(observer);
    }

    public void remove(Observer observer) {
        list.remove(observer);
    }

    public abstract void notice();

}
