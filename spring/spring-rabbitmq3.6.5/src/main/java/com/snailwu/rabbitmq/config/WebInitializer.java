package com.snailwu.rabbitmq.config;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

import static javax.servlet.DispatcherType.REQUEST;

/**
 * @author 吴庆龙
 * @date 2020/5/22 1:41 下午
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(@NonNull ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        servletContext
                // 支持 REST Method
                .addFilter("methodFilter", new HiddenHttpMethodFilter())
                .addMappingForUrlPatterns(EnumSet.of(REQUEST), true, "/*");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    @NonNull
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }
}
