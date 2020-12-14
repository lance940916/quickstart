package com.snailwu.springboot.request;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 吴庆龙
 * @date: 2020/2/10 3:37 下午
 */
@RestController
public class IndexController {

    // ============== 简单的 key:value 请求

    /**
     * 第一种：映射 url 地址中的参数
     * /url_param?name=Mike&age=20
     * 方法参数写法:
     * 1. urlParam(String name, Integer age)
     * 2. urlParam({@link UserVO} user)
     */
    @GetMapping("/url_param")
    public String urlParam(UserVO userVO, HttpServletRequest request) {
        System.out.println("ContentType: " + request.getContentType()); // null
//        System.out.println("请求：" + String.format("Name:%s, Age:%d", name, age));
        System.out.println("请求参数：" + userVO);
        return "Hello Spring.";
    }

    /**
     * 第二种：映射 Header 中的参数
     * 使用 @RequestHeader 来接收 Header 中的参数
     */
    @GetMapping("/header_param")
    public String headerParam(@RequestHeader("name") String name, HttpServletRequest request) {
        System.out.println("ContentType: " + request.getContentType()); // null
        System.out.println("请求参数：" + String.format("Name:%s", name));
        return "Hello Spring.";
    }

    /**
     * 第三种：映射 body 中的参数
     * 使用 multipart/form-data 请求类型来请求
     */
    @PostMapping("/multipart_form_data")
    public String multipartFormData(UserVO userVO,
                                    @RequestParam("image1") MultipartFile image1,
                                    @RequestParam("image2") MultipartFile image2,
                                    HttpServletRequest request) {
        System.out.println("ContentType: " + request.getContentType());
        System.out.println("请求参数: " + userVO);
        System.out.println("Image1: " + image1.getOriginalFilename());
        System.out.println("Image2: " + image2.getOriginalFilename());
        return "Hello Spring.";
    }

    /**
     * 第三种：映射 body 中的参数
     * 使用 multipart/form-data 请求类型来请求
     * userVO 中含有 list 集合属性，请求时使用 list[0] list[1] list[2] 来填写请求参数
     * userVO 中含有 array 数组属性，请求时使用 array[0] array[1] array[2] 来填写请求参数
     */
    @PostMapping("/multipart_form_data1")
    public String multipartFormData1(UserVO userVO,
                                     HttpServletRequest request) {
        System.out.println("ContentType: " + request.getContentType());
        System.out.println("请求参数: " + userVO);
        return "Hello Spring.";
    }

    /**
     * 第三种：映射 body 的参数
     * 使用 application/x-www-form-urlencoded 请求类型来请求
     * userVO 中含有 list 集合属性，请求时使用 list[0] list[1] list[2] 来填写请求参数
     * userVO 中含有 array 数组属性，请求时使用 array[0] array[1] array[2] 来填写请求参数
     */
    @PostMapping("/form")
    public String formData(UserVO userVO, HttpServletRequest request) {
        System.out.println("ContentType: " + request.getContentType());
        System.out.println("请求参数：" + userVO);
        return "Hello Spring.";
    }

    /**
     * 映射 body 的参数
     * application/json
     */
    @PostMapping("/raw_json")
    public String rawJson(@RequestBody UserVO userVO, HttpServletRequest request) {
        System.out.println("ContentType: " + request.getContentType());
        System.out.println("请求参数：" + userVO);
        return "Hello Spring.";
    }

    @PostMapping("/raw_string")
    public String rawString(@RequestBody String content, HttpServletRequest request) {
        System.out.println("ContentType: " + request.getContentType());
        System.out.println("请求参数：" + content);
        return "Hello Spring.";
    }

    @GetMapping("/redirect/url_param")
    public String redirectUrlParam() {
        return "{}";
    }

}
