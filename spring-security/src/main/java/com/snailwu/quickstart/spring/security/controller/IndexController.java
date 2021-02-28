package com.snailwu.quickstart.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 吴庆龙
 * @date: 2020/3/3 4:47 下午
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "Hello Web";
    }

}
