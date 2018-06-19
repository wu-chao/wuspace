package com.github.wuchao.webproject.service.redis;

import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class RedisUtil {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisUtil(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public <T> void set(String field, T obj) {
        String value = JacksonUtil.serializeAsString(obj);
        this.redisTemplate.opsForValue().set(field, value);
    }

    public <T> T get(String field, Class<T> targetClass) {
        String value = redisTemplate.opsForValue().get(field);
        if (StringUtils.isNotEmpty(value)) {
            return JacksonUtil.deserialize(value, targetClass);
        } else {
            return null;
        }
    }

    public List<User> getList(String field, Class<User> targetClass) {
        String value = redisTemplate.opsForValue().get(field);
        if (StringUtils.isNotEmpty(value)) {
            return (List<User>) JacksonUtil.deserializeCollection(value, List.class, targetClass);
        } else {
            return null;
        }
    }

    /**
     * key Generator
     *
     * @param className
     * @param methodName
     * @param params
     * @return
     */
    public static String keyGenerator(String className, String methodName, Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(className).append('.');
        sb.append(methodName).append('.');
        if (params != null && params.length > 0) {
            Arrays.stream(params).forEach(param -> sb.append(param.toString()));
        }
        log.info("调用Redis缓存:Key= " + sb.toString());
        return sb.toString();
    }

}
