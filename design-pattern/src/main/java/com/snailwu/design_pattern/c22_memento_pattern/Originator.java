package com.snailwu.design_pattern.c22_memento_pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * 发起人
 * 创建备忘录、恢复备忘录
 *
 * @author 吴庆龙
 * @date 2020/5/19 4:47 下午
 */
@Getter
@Setter
public class Originator {

    private String state;

    public Memento create() {
        return new Memento(state);
    }

    public void restoreMemento(Memento memento) {
        this.state = memento.getState();
    }

}
