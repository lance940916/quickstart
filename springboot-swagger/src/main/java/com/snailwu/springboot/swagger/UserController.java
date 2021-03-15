package com.snailwu.springboot.swagger;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WuQinglong
 * @date 2021/3/15 10:43
 */
@RestController
public class UserController {

    @ApiOperation("用户Ping")
    @GetMapping("/user/ping")
    public String userPing(@ApiParam("姓名") String name) {
        return "Hello User.";
    }

}
