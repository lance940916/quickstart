package com.snailwu.design_pattern.c2_prototype_pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 吴庆龙
 * @date 2020/5/19 11:15 上午
 */
@Getter
@Setter
@ToString
public class User implements Cloneable {

    private String name;
    private String age;

    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
