package com.github.wuchao.webproject.service.redis;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.redis.RedisUtil;
import com.github.wuchao.webproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.util.Pool;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class JetCacheService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Pool pool;

    public static Cache<String, User> userCache;

    @PostConstruct
    public void init() {
        try {
            Method method = JetCacheService.class.getMethod("invokeMethod", new Class[]{String.class});
            String keyPrefix = RedisUtil.keyGenerator(JetCacheService.class.getName(), method.getName(), method.getParameterTypes());
            JetCacheService.userCache = RedisCacheBuilder.createRedisCacheBuilder()
                    .jedisPool(pool)
                    .keyPrefix(keyPrefix)
                    .expireAfterWrite(60, TimeUnit.SECONDS)
                    .buildCache();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        log.info("-------------- CacheBuilder 创建 Cache 完成 --------------");
    }

    public User invokeMethod(String username) {
        log.info("----------------------------- Caffeine 调用方法 -----------------------------");
        return userRepository.findByUsername(username);
    }

    public User getUser(String username) {
        // GET、GET_ALL这类大写API只纯粹访问缓存，不会调用loader
        return userCache.GET(username).getValue();
    }

    public List<User> getUsers() {
        String key = redisUtil.keyGenerator(this.getClass().getName(),
                "getUsers", new Class[]{}, 0);
        List<User> users = redisUtil.getList(key, User.class);
        if (users == null) {
            users = userRepository.findAll();
            redisUtil.set(key, users);
        }
        return users;
    }

}
