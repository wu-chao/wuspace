package com.github.wuchao.webproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class RedisCacheConfig extends CachingConfigurerSupport {

    private static final String CACHE_NAME = "default";

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
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // 缓存默认过期时间 60s
        cacheManager.setDefaultExpiration(60);

        List<String> cacheNames = new ArrayList<>();
        cacheNames.add(CACHE_NAME);
        cacheManager.setCacheNames(cacheNames);

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
    public KeyGenerator getKeyGenerator() {
        return new SimpleKeyGenerator();
    }

}
