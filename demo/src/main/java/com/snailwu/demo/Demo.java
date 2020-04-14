package com.snailwu.demo;

import java.util.Arrays;
import java.util.List;

/**
 * @author 吴庆龙
 * @date 2020/4/10 1:44 下午
 */
public class Demo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        System.out.println(list.stream().mapToInt(Integer::intValue).average().orElse(0));

    }
}
