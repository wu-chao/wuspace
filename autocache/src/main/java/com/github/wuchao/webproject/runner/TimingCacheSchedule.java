package com.github.wuchao.webproject.runner;

import com.github.wuchao.webproject.common.Constants;
import com.github.wuchao.webproject.redis.CacheSupport;
import com.github.wuchao.webproject.redis.CachedMethodInvocation;
import com.github.wuchao.webproject.redis.RedisUtil;
import com.github.wuchao.webproject.service.redis.JetCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Order
@Slf4j
public class TimingCacheSchedule implements CommandLineRunner {

    @Autowired
    private BeanFactory beanFactory;

    @PostConstruct
    public void init() {
        try {
            String key = RedisUtil.keyGenerator(this.getClass().getName(),
                    "getUser", null, 30);
            CachedMethodInvocation cachedMethodInvocation = new CachedMethodInvocation(key, JetCacheService.class.getName(),
                    JetCacheService.class.getMethod("invokeMethod", String.class), new Class[]{String.class}, Void.class);
            Constants.REDIS_CACHE_METHOD_INVOCATION_MAP.put(key, cachedMethodInvocation);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) {
        CacheSupport cacheSupport = beanFactory.getBean(CacheSupport.class);
        cacheSupport.invokeAllMethods();
    }

}
