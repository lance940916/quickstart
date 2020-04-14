package com.snailwu.demo;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println(calendar.getTimeInMillis());
        calendar.clear(Calendar.MILLISECOND);
        calendar.clear(Calendar.SECOND);
        System.out.println(calendar.getTimeInMillis());
        System.out.println(DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss:sss"));
        System.out.println(calendar.getTimeInMillis());
//        calendar.clear(Calendar.MINUTE);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        calendar.set(Calendar.MONTH, Calendar.JANUARY);
//        System.out.println(DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss:sss"));
//        calendar.add(Calendar.YEAR, 1);
//        System.out.println(DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss:sss"));
//
//        System.out.println(Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));


    }
}
