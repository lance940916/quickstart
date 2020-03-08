package com.snailwu.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 吴庆龙
 * @date: 2020/3/5 2:55 下午
 */
public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("Hello Info");
        new Thread(() -> log.info("Hello Thread")).start();
    }
}
