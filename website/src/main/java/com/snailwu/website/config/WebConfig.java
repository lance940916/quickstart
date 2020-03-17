package com.snailwu.website.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.*;

/**
 * WebMvc 配置
 *
 * @author: 吴庆龙
 * @date: 2020/3/17 9:45 上午
 */
@Slf4j
@Configuration
@Import({
        ThymeleafConfig.class
})
@EnableWebMvc
@ComponentScan("com.snailwu.website.controller")
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置资源文件路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/statics/**").addResourceLocations("/statics/");
        log.info("映射资源文件成功 {}", "/statics/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/", "/index");
    }

}
