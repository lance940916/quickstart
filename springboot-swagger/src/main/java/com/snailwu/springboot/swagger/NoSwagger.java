package com.snailwu.springboot.swagger;

import java.lang.annotation.*;

/**
 * @author WuQinglong
 * @date 2021/3/12 17:57
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoSwagger {
}
