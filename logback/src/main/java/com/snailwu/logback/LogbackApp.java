package com.snailwu.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: 吴庆龙
 * @date: 2020/2/14 2:48 下午
 */
public class LogbackApp {

    private static Logger log = LoggerFactory.getLogger("com.snailwu.logback.LogbackApp");

    public static void main(String[] args) throws InterruptedException {

        MDC.put("name", "Mike");

        log.debug("Debug Msg.");
        log.info("Info Msg.");
        log.warn("Warn Msg.");
        log.error("Error Msg.");

        // 传递给子线程
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        new Thread(() -> {
            MDC.setContextMap(copyOfContextMap);
            log.info("Inner Thread Log.");
        }).start();

        // 提供了 MDCInsertingServletFilter 过滤器，便于快速使用

    }
}
