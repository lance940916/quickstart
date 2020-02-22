package com.snailwu.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 40 ; i++) {
//            log.info("Hello Info");
//            TimeUnit.SECONDS.sleep(1);
//        }

        log.debug("Hello Debug");
        log.info("Hello Info");
        log.warn("Hello Warn");
        log.error("Hello Error");

    }
}
