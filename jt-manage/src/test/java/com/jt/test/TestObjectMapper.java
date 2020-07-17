package com.jt.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author Yuanzhibx
 * @Date 2020-07-17
 */
public class TestObjectMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 实现对象与 Json 串的转化
     * 利用 ObjectMapper 工具 API 实现
     *
     * @throws JsonProcessingException
     */
    @Test
    public void test01() throws JsonProcessingException {
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(1L).setItemDesc("DEMO").setCreated(new Date()).setUpdated(itemDesc.getCreated());

        //1. 将对象转换为 JSON, 调用的是对象的 get 方法
        String json = MAPPER.writeValueAsString(itemDesc);
        System.out.println(json);

        //2. 将 json 转化为对象, 调用的是对象的 set 方法
        ItemDesc itemDesc2 = MAPPER.readValue(json, ItemDesc.class);
        System.out.println(itemDesc2.getItemDesc());
    }

}
