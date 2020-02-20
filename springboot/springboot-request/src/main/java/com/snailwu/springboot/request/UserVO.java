package com.snailwu.springboot.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author: 吴庆龙
 * @date: 2020/2/20 1:44 下午
 */
@Getter
@Setter
@ToString
public class UserVO {

    private String name;
    private Integer age;
    private List<String> list;
    private String[] array;

}
