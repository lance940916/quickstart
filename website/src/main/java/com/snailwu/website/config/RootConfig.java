package com.snailwu.website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: 吴庆龙
 * @date: 2020/3/17 9:44 上午
 */
@Configuration
@Import({
        MyBatisConfig.class
})
public class RootConfig {
}
