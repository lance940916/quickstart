package com.snailwu.springboot.request;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

    @Bean
    public FilterRegistrationBean<AppLogFilter> registerAppLogFilter() {
        FilterRegistrationBean<AppLogFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AppLogFilter());
        bean.addUrlPatterns("/*");
        bean.setName("appLogFilter");
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
