package com.github.wuchao.webproject.redis;

import com.github.wuchao.webproject.common.Constants;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class RedisUtil {

    private final RedisTemplate<String, String> redisTemplate;
    private JedisPool jedisPool = null;
    private static final String DEFAULT_KEYS_PATTERN = "com.github.wuchao.webproject*";

    @Autowired
    public RedisUtil(RedisTemplate<String, String> redisTemplate, JedisPool jedisPool) {
        this.redisTemplate = redisTemplate;
        this.jedisPool = jedisPool;
    }

    /**
     * 获取所有缓存的 Key
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        if (jedisPool != null) {
            return jedisPool.getResource().keys(pattern);
        }
        return null;
    }

    public Set<String> keys() {
        return keys(DEFAULT_KEYS_PATTERN);
    }

    public <T> void set(String field, T obj) {
        String value = JacksonUtil.serializeAsString(obj);
        this.redisTemplate.opsForValue().set(field, value);
    }

    public <T> T getWithoutLock(String field, Class<T> targetClass) {
        String value = redisTemplate.opsForValue().get(field);
        if (StringUtils.isNotEmpty(value)) {
            return JacksonUtil.deserialize(value, targetClass);
        } else {
            return null;
        }
    }

    public <T> T get(String field, Class<T> targetClass) {
        RedisLock redisLock = Constants.REDIS_LOCK_MAP.get(field + "_lock");
        if (redisLock != null && redisLock.lock()) {
            return (T) redisLock.getData();
        } else {
            return getWithoutLock(field, targetClass);
        }
    }

    public List<User> getListWithoutLock(String field, Class<User> targetClass) {
        RedisLock redisLock = Constants.REDIS_LOCK_MAP.get(field);
        if (redisLock != null && redisLock.lock()) {
            return (List<User>) redisLock.getData();
        } else {
            String value = redisTemplate.opsForValue().get(field);
            if (StringUtils.isNotEmpty(value)) {
                return (List<User>) JacksonUtil.deserializeCollection(value, List.class, targetClass);
            } else {
                return null;
            }
        }
    }

    public List<User> getList(String field, Class<User> targetClass) {
        RedisLock redisLock = Constants.REDIS_LOCK_MAP.get(field);
        if (redisLock != null && redisLock.lock()) {
            return (List<User>) redisLock.getData();
        } else {
            return getListWithoutLock(field, targetClass);
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
    public static String keyGenerator(String className, String methodName, Object[] params, long expiration) {
        StringBuilder sb = new StringBuilder();
        sb.append(className).append('.');
        sb.append(methodName).append('.');
        if (params != null && params.length > 0) {
            Arrays.stream(params).forEach(param -> sb.append(param.toString()));
        }
        if (expiration > 0) {
            sb.append("_").append(expiration);
        }
        log.info("调用Redis缓存:Key= " + sb.toString());
        return sb.toString();
    }

}
