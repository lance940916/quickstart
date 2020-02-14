package com.snailwu.springboot.test.template.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 吴庆龙
 * @date: 2020/2/10 3:37 下午
 */
@RestController
public class IndexController {

    @GetMapping("/say")
    public String say() {
        return "say Hello";
    }

}
