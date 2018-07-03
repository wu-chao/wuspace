package com.github.wuchao.webproject.runner;

import com.alicp.jetcache.RefreshPolicy;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.wuchao.webproject.common.CacheConstants;
import com.github.wuchao.webproject.common.Constants;
import com.github.wuchao.webproject.redis.RedisUtil;
import com.github.wuchao.webproject.repository.UserRepository;
import com.github.wuchao.webproject.service.CacheService;
import com.github.wuchao.webproject.service.redis.JetCacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import redis.clients.util.Pool;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 1. 使用 Caffeine 调用一次方法，再定时刷新
 * 2. 重写一个方法，加上 @CacheRefresh 注解
 */
@Component
@Order
@Slf4j
public class TimingCacheSchedule implements CommandLineRunner {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private Pool pool;

    //    @CreateCache


    @Autowired
    private JetCacheService jetCacheService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
//        try {
//            String key = RedisUtil.keyGenerator(this.getClass().getName(),
//                    "getUser", null, 30);
//            CachedMethodInvocation cachedMethodInvocation = new CachedMethodInvocation(key, JetCacheService.class.getName(),
//                    JetCacheService.class.getMethod("invokeMethod", String.class), new Class[]{String.class}, Void.class);
//            Constants.REDIS_CACHE_METHOD_INVOCATION_MAP.put(key, cachedMethodInvocation);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }

        try {
            Method method = JetCacheService.class.getMethod("invokeMethod", new Class[]{String.class});
            String keyPrefix = RedisUtil.keyGenerator(JetCacheService.class.getName(), method.getName(), method.getParameterTypes());
            JetCacheService.userCache = RedisCacheBuilder.createRedisCacheBuilder()
                    .jedisPool(pool)
                    .keyPrefix(keyPrefix)
                    .loader(k -> jetCacheService.invokeMethod(String.valueOf(k)))
                    .refreshPolicy(RefreshPolicy.newPolicy(50, TimeUnit.SECONDS))
                    .expireAfterWrite(60, TimeUnit.SECONDS)
                    .buildCache();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        log.info("-------------- CacheBuilder 创建 Cache 完成 --------------");

        AsyncLoadingCache<Object, Object> cache = Caffeine.newBuilder().
                executor(Constants.CACHE_THREAD_POOL)
                .buildAsync(key -> {
                    // 用get方法取缓存，没有命中的话自己去数据库load
                    return JetCacheService.userCache.get(String.valueOf(key));
                });
        if (cache != null) {
            CacheConstants.CAFFEINE_CACHE_MAP.put("JetCacheService.userCache", cache);
        }
    }

    @Override
    public void run(String... args) {
        if (MapUtils.isNotEmpty(CacheConstants.CAFFEINE_CACHE_MAP)) {
            CacheConstants.CAFFEINE_CACHE_MAP.entrySet().forEach(stringAsyncLoadingCacheEntry -> {
                String key = stringAsyncLoadingCacheEntry.getKey();
                AsyncLoadingCache cache = stringAsyncLoadingCacheEntry.getValue();
                if (StringUtils.isNotBlank(key)) {
                    if (key.indexOf("JetCacheService.userCache") > -1) {
                        List<String> names = userRepository.findAll().stream().map(user -> user.getUsername()).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(names)) {
                            names.stream().forEach(name -> cache.synchronous().refresh(name));
                        }
                    }
                }
            });
        }
    }

}
