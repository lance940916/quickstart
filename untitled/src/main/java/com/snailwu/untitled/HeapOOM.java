package com.snailwu.untitled;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴庆龙
 * @date 2020/11/8 10:19 上午
 */
public class HeapOOM {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>(10000000);
        while (true) {
            list.add(new Object());
        }
    }

}
