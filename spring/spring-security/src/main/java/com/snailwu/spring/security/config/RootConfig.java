package com.snailwu.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: 吴庆龙
 * @date: 2020/3/3 4:47 下午
 */
@Configuration
@Import({
        WebSecurityConfig.class
})
public class RootConfig {
}
