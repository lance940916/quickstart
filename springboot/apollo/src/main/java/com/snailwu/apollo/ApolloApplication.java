package com.snailwu.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableApolloConfig
public class ApolloApplication {

    @Value("${timeout:9999}")
    private Integer timeout;

    @ApolloConfig
    private Config config;

    public static void main(String[] args) {
        SpringApplication.run(ApolloApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println(timeout);
        String name = config.getProperty("name", "NoName");
        System.out.println(name);
    }

}
