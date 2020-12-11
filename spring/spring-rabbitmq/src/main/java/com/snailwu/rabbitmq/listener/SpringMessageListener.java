package com.snailwu.rabbitmq.listener;

import com.snailwu.rabbitmq.entity.User;

/**
 * @author 吴庆龙
 * @date 2020/12/10 下午4:29
 */
public class SpringMessageListener {

//    public void handleMessage(byte[] body) {
//        System.out.println(new String(body, StandardCharsets.UTF_8));
//    }

    /**
     * 使用 TextMessageConverter 参数是 String
     */
//    public void handleMessage(String body) {
//        System.out.println(body);
//    }

//    public void beanQueueMessage(byte[] body) {
//        System.out.println(new String(body, StandardCharsets.UTF_8));
//    }

    public void handleMessage(User user) {
        System.out.println(user);
    }

}
