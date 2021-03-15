package com.snailwu.springboot.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 小孩信息接口
 * 哈哈哈
 * 嘿嘿额黑
 *
 * @author WuQinglong
 * @date 2021/3/8 14:32
 */
@Api(value = "主页接口")
@RestController
public class IndexController {

    /**
     * 获取小孩信息
     *
     * @param search 查询条件
     * @return 响应
     */
    @ApiOperation(value = "获取小孩信息")
    @GetMapping("/child")
    public String getChild(Search search, @ApiParam(hidden = true) @RequestHeader HttpHeaders headers) {
        System.out.println(search);
        Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return "Hello Swagger.";
    }

    /**
     * 新增小孩
     *
     * @param child 小孩信息
     * @return 响应
     */
    @ApiOperation(value = "新增小孩", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/child")
    public String addChild(@RequestBody Child child) {
        return "Hello Swagger.";
    }

}
