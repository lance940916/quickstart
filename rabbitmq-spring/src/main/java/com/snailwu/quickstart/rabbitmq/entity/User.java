package com.snailwu.quickstart.rabbitmq.entity;

import java.io.Serializable;

/**
 * @author 吴庆龙
 * @date 2020/12/11 下午2:13
 */
public class User implements Serializable {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
