package com.snailwu.springboot.swagger;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小孩信息接口
 * 哈哈哈
 * 嘿嘿额黑
 *
 * @author WuQinglong
 * @date 2021/3/8 14:32
 */
@Api
@RestController
public class IndexController {

    /**
     * 获取小孩信息
     *
     * @param search 查询条件
     * @return 响应
     */
    @GetMapping("/child")
    public String getChild(Search search) {
        return "Hello Swagger.";
    }

    /**
     * 新增小孩
     *
     * @param child 小孩信息
     * @return 响应
     */
    @PostMapping("/child")
    public String addChild(@RequestBody Child child) {
        return "Hello Swagger.";
    }

}
