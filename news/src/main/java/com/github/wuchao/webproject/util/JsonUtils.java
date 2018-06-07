package com.github.wuchao.webproject.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Json 教程：https://www.sojson.com/json/json_index.html
 * java Json 串中的转义字符：https://zhidao.baidu.com/question/1819563224903426748.html
 */
public abstract class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            // new module, NOT JSR310Module
            .registerModule(new JavaTimeModule())
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    /**
     * Jackson：对象转 json 字符串
     *
     * @param obj
     * @return
     */
    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Jackson：json 字符串解析成对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static Object json2obj(String json, Class clazz) {
        Object obj;
        try {
            // Json 形式："{\"username\":\"test_user1\",\"email\":\"111@qq.com\",\"avatarUrl\":\"http://www.baidu.com/image.jpg\"}";
            obj = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            try {
                // Json 形式："{\"user\":{\"username\":\"test_user2\",\"email\":\"222@qq.com\",\"avatarUrl\":\"http://www.baidu.com/image.jpg\"}}";
                obj = objectMapper.readValue(new JSONObject(json).getString(StringUtils.toLowerCaseFirstOne(clazz.getSimpleName())), clazz);
            } catch (IOException | JSONException e1) {
                e1.printStackTrace();
                obj = null;
            }
        }

        return obj;
    }

}
