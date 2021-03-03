package com.snailwu.quickstart.spring.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 吴庆龙
 * @date 2020/3/3 4:48 下午
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.snailwu.quickstart.spring.security.controller")
@Import({
        WebSecurityConfig.class
})
public class WebConfig implements WebMvcConfigurer {

}