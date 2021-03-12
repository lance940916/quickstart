package com.snailwu.springboot.swagger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author WuQinglong
 * @date 2021/3/8 14:43
 */
public class Search {

    /**
     * 姓名
     */
    @JsonProperty("user_name")
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
