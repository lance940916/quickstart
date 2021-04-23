package com.snailwu.start.io;

import java.nio.channels.SelectionKey;

/**
 * @author WuQinglong
 * @date 2021/4/15 14:23
 */
public class Demo {
    public static void main(String[] args) {

        System.out.println(SelectionKey.OP_READ);
        System.out.println(SelectionKey.OP_ACCEPT);
        System.out.println(SelectionKey.OP_WRITE);

    }
}
