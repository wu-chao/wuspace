package com.github.wuchao.webproject.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Json 教程：https://www.sojson.com/json/json_index.html
 * java Json 串中的转义字符：https://zhidao.baidu.com/question/1819563224903426748.html
 */
public abstract class JacksonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            // new module, NOT JSR310Module
            .registerModule(new JavaTimeModule())
            // .findAndRegisterModules(); 会导致对象中加了@Transient注解的字段无法被序列化
            // 为null的字段不序列化
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            // to avoid exception(No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered to create BeanSerializer)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    /**
     * Jackson：对象转 json 字符串
     *
     * @param obj
     * @return
     */
    public static String serializeAsString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Jackson：对象转 byte[]
     *
     * @param obj
     * @return
     */
    public static byte[] serializeAsBytes(Object obj) {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Jackson：json 字符串解析成对象
     *
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T deserialize(String jsonStr, Class<T> clazz) {
        T obj;
        try {
            // Json 形式："{\"username\":\"test_user1\",\"email\":\"111@qq.com\",\"avatarUrl\":\"http://www.baidu.com/image.jpg\"}";
            obj = objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            try {
                // Json 形式："{\"user\":{\"username\":\"test_user2\",\"email\":\"222@qq.com\",\"avatarUrl\":\"http://www.baidu.com/image.jpg\"}}";
                obj = objectMapper.readValue(new JSONObject(jsonStr).getString(StringUtils.toLowerCaseFirstOne(clazz.getSimpleName())), clazz);
            } catch (IOException | JSONException e1) {
                e1.printStackTrace();
                obj = null;
            }
        }

        return obj;
    }

    /**
     * Jackson：json byte[] 解析成对象
     *
     * @param jsonBytes
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] jsonBytes, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonBytes, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json 字符串反序列化 Collection
     *
     * @param jsonStr
     * @param collectionClass
     * @param elementClasses
     * @param <T>
     * @return
     */
    public static <T> Collection<T> deserializeCollection(String jsonStr, Class collectionClass, Class<T>... elementClasses) {
        try {
            return objectMapper.readValue(jsonStr, getCollectionType(objectMapper, collectionClass, elementClasses));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json byte[] 反序列化 Collection
     *
     * @param jsonBytes
     * @param collectionClass
     * @param elementClasses
     * @param <T>
     * @return
     */
    public static <T> Collection<T> deserializeCollection(byte[] jsonBytes, Class collectionClass, Class<T>... elementClasses) {
        try {
            return objectMapper.readValue(jsonBytes, getCollectionType(objectMapper, collectionClass, elementClasses));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 反序列化 Map
     *
     * @param json
     * @param mapClass
     * @param elementClasses
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> deserializeMap(String json, Class mapClass, Class<T>... elementClasses) {
        try {
            return objectMapper.readValue(json, getCollectionType(objectMapper, mapClass, elementClasses));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取泛型的 Collection Type
     * http://hw1287789687.iteye.com/blog/2228897
     *
     * @param collectionClass
     * @param elementClasses
     * @return JavaType
     * @since 1.0
     */
    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
