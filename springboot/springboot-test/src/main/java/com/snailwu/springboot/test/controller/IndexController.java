package com.snailwu.springboot.test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 吴庆龙
 * @date: 2020/2/10 3:37 下午
 */
@RestController
public class IndexController {

    @Value("${db.name}")
    private String dbName;

    @GetMapping("/index")
    public String index() {
        System.out.println("=====dbName: " + dbName);
        return "Hello Springboot.";
    }

}
