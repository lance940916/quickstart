package com.snailwu.springboot.swagger;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author WuQinglong
 * @date 2021/3/8 14:43
 */
@ApiModel(value = "搜索类")
public class Search {

    /**
     * 姓名
     */
    @ApiModelProperty("用户名")
    @JsonProperty("user_name")
    private String userName;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", example = "10")
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

    @Override
    public String toString() {
        return "Search{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
