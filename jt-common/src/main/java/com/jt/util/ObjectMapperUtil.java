package com.jt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

/**
 * @author Yuanzhibx
 * @Date 2020-07-17
 */
public class ObjectMapperUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转化为 JSON 串
     */
    public static String toJson(Object target) {
        if (target == null) {
            throw new NullPointerException("target 数据为空~");
        }
        try {
            return MAPPER.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //如果转化过程中有问题, 直接抛出异常
            throw new RuntimeException(e);
        }
    }

    /**
     * 将 JSON 串转化为对象
     * 用户传递什么类型, 就返回什么对象
     *
     * <T> T 定义了一个泛型对象
     */
    public static <T> T toObject(String json, Class<T> targetClass) {
        if (StringUtils.isEmpty(json) || targetClass == null) {
            throw new NullPointerException("target 数据为空~");
        }
        try {
            return MAPPER.readValue(json, targetClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
