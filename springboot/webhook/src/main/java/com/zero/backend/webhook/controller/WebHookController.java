package com.zero.backend.webhook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吴庆龙
 * @date 2020/8/27 3:05 下午
 */
@RestController
@RequestMapping("/web_hook")
public class WebHookController {

    @GetMapping("/merge_request")
    public String mergeRequest(String content) {
        return null;
    }

}
