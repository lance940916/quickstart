package com.snailwu.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 吴庆龙
 * @date: 2020/2/14 3:07 下午
 */
public class FileLogApp {

    public static final Logger log = LoggerFactory.getLogger(FileLogApp.class);

    public static void main(String[] args) {
        log.debug("Debug Msg.");
        log.info("Info Msg.");
        log.warn("Warn Msg.");
        log.error("Error Msg.");
    }

}
