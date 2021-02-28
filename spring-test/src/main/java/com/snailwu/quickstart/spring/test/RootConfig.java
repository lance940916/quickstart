package com.snailwu.quickstart.spring.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 配置 Spring 后端应用
 *
 * @author 吴庆龙
 * @date 2019/11/25 6:16 下午
 */
@Configuration
@PropertySource("classpath:config.properties")
public class RootConfig {

    @Value("${db.name}")
    private String dbName;

    @Bean
    public String dbName() {
        System.out.println("dbName: " + dbName);
        return dbName;
    }

}
