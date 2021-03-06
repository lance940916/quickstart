package com.snailwu.maven.archetype.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吴庆龙
 * @date 2020/8/20 2:51 下午
 */
@RestController
public class IndexController {

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "Hello Spring.";
    }

}
