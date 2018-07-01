package com.github.wuchao.webproject.config;

import com.alicp.jetcache.anno.CacheConsts;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.anno.support.CacheNameGenerator;
import com.alicp.jetcache.anno.support.DefaultCacheNameGenerator;
import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.embedded.EmbeddedCacheBuilder;
import com.alicp.jetcache.embedded.LinkedHashMapCacheBuilder;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.alicp.jetcache.support.FastjsonKeyConvertor;
import com.alicp.jetcache.support.JavaValueDecoder;
import com.alicp.jetcache.support.JavaValueEncoder;
import com.github.wuchao.webproject.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * JetCache 方法 news 模块，则其他模块启动不起来
 * JetCache 缓存的 key 要和 redis 的 key 使用相同的生成规则
 * JetCache 自定义序列化（Jackson），便于和 redis 这边使用
 */
@Slf4j
@Configuration
@EnableMethodCache(basePackages = "com.github.wuchao.webproject")
@EnableCreateCacheAnnotation
public class JetCacheConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private int maxWaitMillis;

    @Bean
    public Pool<Jedis> pool() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMinIdle(2);
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMaxTotal(maxWaitMillis);
        return new JedisPool(genericObjectPoolConfig, host, port);
    }

    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider() {
            @Override
            public CacheNameGenerator createCacheNameGenerator(String[] hiddenPackages) {
                return new CustomCacheNameGenerator(hiddenPackages);
            }

//            @Override
//            public Function<Object, byte[]> parseValueEncoder(String valueEncoder) {
//                if(valueEncoder.equals("xxx")){
//                    return MyEncoder();
//                }else{
//                    return super.parseValueEncoder(valueEncoder);
//                }
//            };
//            @Override
//            public Function<byte[], Object> parseValueDecoder(String valueDecoder) {
//                   .........
//            }
        };
    }

    @Bean
    public GlobalCacheConfig globalCacheConfig(SpringConfigProvider configProvider, Pool<Jedis> pool) {
        Map localBuilders = new HashMap();
        EmbeddedCacheBuilder localBuilder = LinkedHashMapCacheBuilder
                .createLinkedHashMapCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE);
        localBuilders.put(CacheConsts.DEFAULT_AREA, localBuilder);

        Map remoteBuilders = new HashMap();
        RedisCacheBuilder remoteCacheBuilder = RedisCacheBuilder.createRedisCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                .valueEncoder(JavaValueEncoder.INSTANCE)
                .valueDecoder(JavaValueDecoder.INSTANCE)
                .jedisPool(pool);
        remoteBuilders.put(CacheConsts.DEFAULT_AREA, remoteCacheBuilder);

        GlobalCacheConfig globalCacheConfig = new GlobalCacheConfig();
        globalCacheConfig.setConfigProvider(configProvider);
        globalCacheConfig.setLocalCacheBuilders(localBuilders);
        globalCacheConfig.setRemoteCacheBuilders(remoteBuilders);
        globalCacheConfig.setStatIntervalMinutes(15);
        globalCacheConfig.setAreaInCacheName(false);

        return globalCacheConfig;
    }

    public class CustomCacheNameGenerator extends DefaultCacheNameGenerator {

        public CustomCacheNameGenerator(String[] hiddenPackages) {
            super(hiddenPackages);
        }

        /**
         * 最终的 key 是 cacheName + key，该方法是自定义 cacheName
         *
         * @param method
         * @param targetObject
         * @return
         */
        @Override
        public String generateCacheName(Method method, Object targetObject) {
            String cacheName = cacheNameMap.get(method);
            if (cacheName == null) {
                String className = method.getDeclaringClass().getName();
                String methodName = method.getName();
                Class[] params = method.getParameterTypes();
                String str = RedisUtil.keyGenerator(className, methodName, params);
                cacheNameMap.put(method, str);
                return str;
            }

            return cacheName;
        }
    }

}
