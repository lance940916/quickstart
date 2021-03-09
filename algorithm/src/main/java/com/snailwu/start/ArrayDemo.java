package com.snailwu.start;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuQinglong
 * @date 2021/2/23 11:24
 */
public class ArrayDemo {
    public static int[] retainAll(int[] array1, int[] array2) {
        if (array1.length == 0 || array2.length == 0) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < array1.length && j < array2.length;) {
            int i1 = array1[i];
            int j1 = array2[j];
            if (i1 < j1) {
                i++;
            } else if (i1 > j1) {
                j++;
            } else {
                // 相等
                list.add(i1);
                i++;
                j++;
            }
        }
        int[] resultArray = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resultArray[i] = list.get(i);
        }
        return resultArray;
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 7, 8, 9};
        int[] b = {5, 8, 9, 6};
        int[] c = retainAll(a, b);
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }

    }
}
