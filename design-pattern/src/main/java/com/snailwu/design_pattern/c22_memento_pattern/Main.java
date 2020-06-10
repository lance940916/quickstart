package com.snailwu.design_pattern.c22_memento_pattern;

/**
 * @author 吴庆龙
 * @date 2020/5/19 4:51 下午
 */
public class Main {
    public static void main(String[] args) {
        Originator o = new Originator();
        Caretaker c = new Caretaker();

        o.setState("步骤 A");
        System.out.println("初始状态: " + o.getState());

        Memento memento = o.create();
        c.setMemento(memento);

        o.setState("步骤 B");
        System.out.println("新的状态: " + o.getState());

        o.restoreMemento(c.getMemento());
        System.out.println("恢复状态: " + o.getState());
    }
}
