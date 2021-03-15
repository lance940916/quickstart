package com.snailwu.springboot.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

/**
 * @author WuQinglong
 * @date 2021/3/12 17:50
 */
@Configuration
//@Profile({"test"})
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .apiInfo(apiInfo())
//                .securitySchemes(Collections.singletonList(apiKey()))
                .securitySchemes(Arrays.asList(
                        new ApiKey("mykey1", "api_key1", "header"),
                        new ApiKey("mykey2", "api_key2", "header"),
                        new ApiKey("mykey3", "api_key3", "header")
                ))
                .securityContexts(Arrays.asList(

                ))
                ;
    }

    private ApiKey apiKey() {
        return new ApiKey("mykey", "api_key", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "合谷接口文档",
                "",
                "1.0",
                "",
                DEFAULT_CONTACT,
                "",
                "",
                Collections.emptyList());
    }


}
