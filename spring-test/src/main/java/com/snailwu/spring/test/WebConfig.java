package com.snailwu.spring.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置 SpringMVC
 *
 * @author 吴庆龙
 * @date 2019/11/25 6:51 下午
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.snailwu.spring.test")
public class WebConfig implements WebMvcConfigurer {
}
