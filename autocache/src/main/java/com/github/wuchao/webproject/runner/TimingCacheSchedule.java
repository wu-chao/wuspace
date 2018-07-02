package com.github.wuchao.webproject.runner;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.RefreshPolicy;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.wuchao.webproject.common.CacheConstants;
import com.github.wuchao.webproject.common.Constants;
import com.github.wuchao.webproject.domain.User;
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
    private Cache<String, User> userCache;

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

        AsyncLoadingCache<Object, Object> cache = Caffeine.newBuilder().
                executor(Constants.CACHE_THREAD_POOL)
                .buildAsync(key -> {
                    cacheService.findByUsername(String.valueOf(key));
                    return jetCacheService.invokeMethod(String.valueOf(key));

                });

        if (cache != null) {
            CacheConstants.CAFFEINE_CACHE_MAP.put("findDamSafetyCheckById_param_600", cache);

            RefreshPolicy refreshPolicy = RefreshPolicy.newPolicy(50, TimeUnit.SECONDS);
            try {
                Method method = jetCacheService.getClass().getMethod("invokeMethod", new Class[]{String.class});
                String keyPrefix = RedisUtil.keyGenerator(method.getClass().getName(), method.getName(), method.getParameterTypes());
                userCache = RedisCacheBuilder.createRedisCacheBuilder()
                        .jedisPool(pool)
                        .keyPrefix(keyPrefix)
                        .loader(key -> jetCacheService.invokeMethod(String.valueOf(key)))
                        .refreshPolicy(refreshPolicy)
                        .expireAfterWrite(60, TimeUnit.SECONDS)
                        .buildCache();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run(String... args) {
        if (MapUtils.isNotEmpty(CacheConstants.CAFFEINE_CACHE_MAP)) {
            CacheConstants.CAFFEINE_CACHE_MAP.entrySet().forEach(stringAsyncLoadingCacheEntry -> {
                String key = stringAsyncLoadingCacheEntry.getKey();
                AsyncLoadingCache cache = stringAsyncLoadingCacheEntry.getValue();
                if (StringUtils.isNotBlank(key)) {
                    if (key.indexOf("findDamSafetyCheckById") > -1) {
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
