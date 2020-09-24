package com.snailwu.untitled;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        String result1 = "{\"code\":1000,\"result\":true,\"msg\":\"返回成功\",\"data\":{\"406ffcb8-2f37-4b4c-b140-32a56818edad\":[{\"BudgetID\":\"92bcec18-ae95-ea11-80be-4eeb76793f90\",\"BudgetCode\":\"33300531\",\"ClientCode\":\"\",\"BudgetName\":\"线上点播课人发测试有效期3个月-上海-2020-05-14-项目预算\",\"NaturalCycle\":\"2020财年\",\"BudCycleCode\":null,\"AvailableAmt\":10000.0,\"BudgetAmt\":10000.0,\"LockAmt\":0.0,\"OccupyAmt\":0.0,\"ControlWay\":2,\"BudType\":2}]}}";
//        System.out.println(result1);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        System.out.println(calendar.getTimeInMillis());
//        calendar.clear(Calendar.MILLISECOND);
//        calendar.clear(Calendar.SECOND);
//        System.out.println(calendar.getTimeInMillis());
//        System.out.println(DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss:sss"));
//        System.out.println(calendar.getTimeInMillis());
//        calendar.clear(Calendar.MINUTE);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        calendar.set(Calendar.MONTH, Calendar.JANUARY);
//        System.out.println(DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss:sss"));
//        calendar.add(Calendar.YEAR, 1);
//        System.out.println(DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss:sss"));
//
//        System.out.println(Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));

        System.out.println(RandomStringUtils.randomAlphanumeric(30));

    }
}
