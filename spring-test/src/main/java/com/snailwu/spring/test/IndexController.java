package com.snailwu.spring.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吴庆龙
 * @date 2020/2/10 12:47 下午
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "Hello Spring.";
    }

}
