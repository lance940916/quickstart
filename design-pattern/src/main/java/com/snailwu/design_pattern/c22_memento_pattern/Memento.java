package com.snailwu.design_pattern.c22_memento_pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 备忘录
 *
 * @author 吴庆龙
 * @date 2020/5/19 4:48 下午
 */
@Getter
@Setter
@ToString
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }
}
