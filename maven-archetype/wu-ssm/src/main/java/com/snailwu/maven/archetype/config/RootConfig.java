package com.snailwu.maven.archetype.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 吴庆龙
 * @date 2020/5/22 1:41 下午
 */
@Configuration
@ComponentScan({
})
@Import({
        DataSourceConfig.class,
        MyBatisConfig.class
})
public class RootConfig {

}
