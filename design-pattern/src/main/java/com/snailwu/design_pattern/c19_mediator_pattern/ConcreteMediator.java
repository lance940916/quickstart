package com.snailwu.design_pattern.c19_mediator_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:16 下午
 */
public class ConcreteMediator implements Mediator {
    private List<Colleague> list = new ArrayList<>();

    @Override
    public void register(Colleague colleague) {
        if (!list.contains(colleague)) {
            list.add(colleague);
            colleague.setMediator(this);
        }
    }

    @Override
    public void replay(Colleague colleague) {
        for (Colleague c : list) {
            if (!c.equals(colleague)) {
                c.receive();
            }
        }
    }
}
