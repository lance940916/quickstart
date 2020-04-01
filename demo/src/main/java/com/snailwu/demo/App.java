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
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MINUTE);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        System.out.println(DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss:sss"));
        calendar.add(Calendar.YEAR, 1);
        System.out.println(DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss:sss"));

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(6);
        list.sort(Comparator.reverseOrder());
        System.out.println(list);

        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("a", 1);
        map.put("b", 3);
        map.put("c", 2);
        map.put("d", 4);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
