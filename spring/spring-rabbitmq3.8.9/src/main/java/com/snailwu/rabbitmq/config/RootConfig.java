package com.snailwu.rabbitmq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 吴庆龙
 * @date 2020/5/22 1:41 下午
 */
@Configuration
@Import({
//        RabbitMQConfig.class,
        AnnotationRabbitMQConfig.class,
})
public class RootConfig {

}
