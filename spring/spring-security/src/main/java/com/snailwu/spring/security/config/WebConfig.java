package com.snailwu.spring.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author: 吴庆龙
 * @date: 2020/3/3 4:48 下午
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.snailwu.spring.security.controller")
public class WebConfig {
}
