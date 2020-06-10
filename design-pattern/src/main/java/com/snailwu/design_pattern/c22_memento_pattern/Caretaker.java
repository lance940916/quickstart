package com.snailwu.design_pattern.c22_memento_pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理备忘录角色
 * 保存、获取备忘录
 *
 * @author 吴庆龙
 * @date 2020/5/19 4:49 下午
 */
@Getter
@Setter
public class Caretaker {
    private Memento memento;
}
