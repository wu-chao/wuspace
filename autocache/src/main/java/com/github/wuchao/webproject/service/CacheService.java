package com.github.wuchao.webproject.service;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.RefreshPolicy;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.wuchao.webproject.common.CacheConstants;
import com.github.wuchao.webproject.common.Constants;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.redis.RedisUtil;
import com.github.wuchao.webproject.repository.UserRepository;
import com.github.wuchao.webproject.service.redis.JetCacheService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.util.Pool;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private Pool pool;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JetCacheService jetCacheService;

    // 加个 name 属性，userRepository.findByUsername 中的可以覆盖此缓存？不能
//    @Cached(expire = 60)
//    @CacheRefresh(refresh = 50)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
