package com.snailwu.untitled;

/**
 * Hello world!
 */
public class App {
    public static final String publicStaticFinalString = "public static final String";
    protected static String protectedStaticString = "protected static String";
    private String name;
    private String address = "Beijing";
    private int age1;
    private int age2 = 13;

    public static void main(String[] args) {
        System.out.println(Child.name);
        int a = 3;
        int b = 5;
        int c = a + b;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    private void f1() {
        System.out.println("Hello F1");
    }

    private void f2() {
        String str = "Hello F2";
        System.out.println(str);
        str += " F2...";
        System.out.println(str);
    }

}
