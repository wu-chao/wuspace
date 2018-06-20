package com.github.wuchao.webproject.config.redis;

import com.github.wuchao.webproject.util.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;

import java.util.Collection;

/**
 * 自定义的redis缓存管理器
 * 支持方法上配置过期时间
 * 支持热加载缓存：缓存即将过期时主动刷新缓存
 */
@Slf4j
public class CustomizedRedisCacheManager extends RedisCacheManager {

    /**
     * 父类cacheMap字段
     */
    private static final String SUPER_FIELD_CACHEMAP = "cacheMap";

    /**
     * 父类dynamic字段
     */
    private static final String SUPER_FIELD_DYNAMIC = "dynamic";

    /**
     * 父类cacheNullValues字段
     */
    private static final String SUPER_FIELD_CACHENULLVALUES = "cacheNullValues";

    /**
     * 父类updateCacheNames方法
     */
    private static final String SUPER_METHOD_UPDATECACHENAMES = "updateCacheNames";

    /**
     * 缓存参数的分隔符
     * 数组元素0=缓存的名称
     * 数组元素1=缓存过期时间TTL
     * 数组元素2=缓存在多少秒开始主动失效来强制刷新
     */
    private static final String SEPARATOR = "#";

    /**
     * SpEL标示符
     */
    private static final String MARK = "$";

    private RedisCacheManager redisCacheManager = null;

    @Autowired
    DefaultListableBeanFactory beanFactory;

    public CustomizedRedisCacheManager(RedisOperations redisOperations) {
        super(redisOperations);
    }

    public CustomizedRedisCacheManager(RedisOperations redisOperations, Collection<String> cacheNames) {
        super(redisOperations, cacheNames);
    }

    public RedisCacheManager getRedisCacheManager() {
        if (redisCacheManager == null) {
            redisCacheManager = beanFactory.getBean(RedisCacheManager.class);
        }
        return redisCacheManager;
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        return cache;
    }

    /**
     * 创建缓存
     *
     * @param cacheName            缓存名称
     * @param expirationSecondTime 过期时间
     * @param preloadSecondTime    制动刷新时间
     * @return
     */
    public CustomizedRedisCache getMissingCache(String cacheName, long expirationSecondTime, long preloadSecondTime) {
        log.info("缓存 cacheName：{}，过期时间:{}, 自动刷新时间:{}", cacheName, expirationSecondTime, preloadSecondTime);
        Boolean dynamic = (Boolean) ReflectionUtils.getFieldValue(getRedisCacheManager(), SUPER_FIELD_DYNAMIC);
        Boolean cacheNullValues = (Boolean) ReflectionUtils.getFieldValue(getRedisCacheManager(), SUPER_FIELD_CACHENULLVALUES);
        return dynamic ? new CustomizedRedisCache(cacheName, (this.isUsePrefix() ? this.getCachePrefix().prefix(cacheName) : null),
                this.getRedisOperations(), expirationSecondTime, preloadSecondTime, cacheNullValues) : null;
    }

    /**
     * 获取过期时间
     *
     * @return
     */
    public long getExpirationSecondTime(String[] cacheParams) {
        if (cacheParams == null || cacheParams.length == 0) {
            return 0;
        }

        // 有效时间，初始化获取默认的有效时间
        Long expirationSecondTime = this.computeExpiration(cacheParams[0]);

        // 设置key有效时间
        if (cacheParams.length > 1) {
            String expirationStr = cacheParams[1];
            if (!StringUtils.isEmpty(expirationStr)) {
                // 支持配置过期时间使用EL表达式读取配置文件时间
                if (expirationStr.contains(MARK)) {
                    expirationStr = beanFactory.resolveEmbeddedValue(expirationStr);
                }
                expirationSecondTime = Long.parseLong(expirationStr);
            }
        }

        return expirationSecondTime < 0 ? 0 : expirationSecondTime;
    }

    /**
     * 获取自动刷新时间
     *
     * @return
     */
    private long getPreloadSecondTime(String[] cacheParams) {
        // 自动刷新时间，默认是0
        Long preloadSecondTime = 0L;
        // 设置自动刷新时间
        if (cacheParams.length > 2) {
            String preloadStr = cacheParams[2];
            if (!StringUtils.isEmpty(preloadStr)) {
                // 支持配置刷新时间使用EL表达式读取配置文件时间
                if (preloadStr.contains(MARK)) {
                    preloadStr = beanFactory.resolveEmbeddedValue(preloadStr);
                }
                preloadSecondTime = Long.parseLong(preloadStr);
            }
        }
        return preloadSecondTime < 0 ? 0 : preloadSecondTime;
    }

}
