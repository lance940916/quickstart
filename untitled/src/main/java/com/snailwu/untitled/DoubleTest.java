package com.snailwu.untitled;

/**
 * @author 吴庆龙
 * @date 2020/11/13 4:08 下午
 */
public class DoubleTest {

    public static void main(String[] args) {
        f1();
        f2();
    }

    private strictfp static void f1() {
        System.out.println( 0.3 / 0.1 );
    }

    private static void f2() {
        System.out.println( 0.3 / 0.1 );
    }

}
