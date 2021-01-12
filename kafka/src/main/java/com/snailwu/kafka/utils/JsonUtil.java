package com.snailwu.kafka.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * 使用 Jackson 进行 JSON 数据的序列化、反序列化
 *
 * @author 吴庆龙
 * @date 2020/5/22 3:11 下午
 */
public class JsonUtil {

    /**
     * 实例化 ObjectMapper
     */
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .enable(SerializationFeature.CLOSE_CLOSEABLE);

    /**
     * 将对象转为 String 类型的 Json
     *
     * @param value 待转换的对象
     * @return JSON数据
     */
    public static String writeValueAsString(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将对象转为 byte 类型的 Json
     *
     * @param value 带转换的对象
     * @return JSON数据
     */
    public static byte[] writeValueAsByte(Object value) {
        try {
            return MAPPER.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取 Json 数据转为对象
     *
     * @param content       byte数据
     * @param typeReference 对象类型
     * @param <T>           对象泛型
     * @return 对象
     */
    public static <T> T readValue(byte[] content, TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(content, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取 Json 数据转为对象
     *
     * @param content   byte数据
     * @param valueType 对象类型
     * @param <T>       对象泛型
     * @return 对象
     */
    public static <T> T readValue(byte[] content, Class<T> valueType) {
        try {
            return MAPPER.readValue(content, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
