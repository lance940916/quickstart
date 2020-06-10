package com.snailwu.design_pattern.c17_state_pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 吴庆龙
 * @date 2020/5/20 1:40 下午
 */
@Getter
@Setter
public class Context {

    private State state;

    public Context(State state) {
        this.state = state;
    }

    public void handle() {
        state.handle();
    }

}
