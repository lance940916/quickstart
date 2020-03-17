package com.snailwu.website.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Json 工具类
 *
 * @author: 吴庆龙
 * @date: 2020/3/17 1:11 下午
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setDateFormat(simpleDateFormat);
    }

    /**
     * 序列化对象 => 字符串
     */
    public String writeToString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("序列化对象失败", e);
            return null;
        }
    }

    /**
     * 序列化对象 => 字节数组
     */
    public byte[] writeToBytes(Object obj) {
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            log.error("序列化对象失败", e);
            return null;
        }
    }

    /**
     * 序列化对象 => 文件
     */
    public void writeToFile(Object object, File file) {
        try {
            mapper.writeValue(file, object);
        } catch (IOException e) {
            log.error("序列化对象失败", e);
        }
    }

    /**
     * 反序列化
     */
    public <T> T read(byte[] bytes, Class<T> clazz) {
        if (bytes.length == 0) {
            return null;
        }
        try {
            return mapper.readValue(bytes, clazz);
        } catch (IOException e) {
            log.error("反序列化对象失败", e);
            return null;
        }
    }

    /**
     * 反序列化
     */
    public <T> T read(byte[] bytes, TypeReference<T> typeReference) {
        if (bytes.length == 0) {
            return null;
        }
        try {
            return mapper.readValue(bytes, typeReference);
        } catch (IOException e) {
            log.error("反序列化对象失败", e);
            return null;
        }
    }

    /**
     * 反序列化
     */
    public <T> T read(String json, Class<T> clazz) {
        if (json == null || json.length() == 0) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("反序列化对象失败", e);
            return null;
        }
    }

    /**
     * 反序列化
     */
    public <T> T read(String json, TypeReference<T> typeReference) {
        if (json == null || json.length() == 0) {
            return null;
        }
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            log.error("反序列化对象失败", e);
            return null;
        }
    }

}
