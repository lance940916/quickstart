package com.snailwu.springboot.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

/**
 * @author WuQinglong
 * @date 2021/3/12 17:50
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.snailwu.springboot.swagger"))
                .build()
                .apiInfo(apiInfo())
                .host("http://localhost:8080")
                .securitySchemes(Collections.singletonList(apiKey()))
                ;
    }

    private ApiKey apiKey() {
        return new ApiKey("mykey", "api_key", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "接口文档",
                "接口文档描述",
                "1.0",
                "",
                DEFAULT_CONTACT,
                "",
                "",
                Collections.emptyList());
    }


}
