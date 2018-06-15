package com.github.wuchao.webproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collections;

@Configuration

@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);

        // 设置数据序列化方式
//        ObjectMapper objectMapper = new ObjectMapper()
//                .registerModule(new ParameterNamesModule())
//                .registerModule(new Jdk8Module())
//                // new module, NOT JSR310Module
//                .registerModule(new JavaTimeModule())
//                .registerModule(new Hibernate5Module().configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, false))
//                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
//                // 忽略json字符串中不识别的属性
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//                // 忽略无法转换的对象 "No serializer found for class com.xxx.Xxx"
//                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new Hibernate5Module());
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);

//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        jackson2JsonRedisSerializer.setObjectMapper(new ObjectMapper()
//                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
//                .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
//                .registerModule(new Hibernate5Module())
//                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
//        );

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
//        redisTemplate.setDefaultSerializer(genericJackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate, Collections.emptyList(), true);
        cacheManager.setUsePrefix(true);
        // 缓存默认过期时间 60s
        cacheManager.setDefaultExpiration(60);
        return cacheManager;
    }

    /**
     * Better to use own Keygenerator instead of default as the default
     * ignores the method and class name in the key generation logic. Using
     * Default for simplicity.
     *
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            log.info("调用Redis缓存:Key= " + sb.toString());
            return sb.toString();
        };
    }

}
