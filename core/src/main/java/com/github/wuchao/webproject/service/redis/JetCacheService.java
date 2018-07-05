package com.github.wuchao.webproject.service.redis;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheGetResult;
import com.alicp.jetcache.anno.CreateCache;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.redis.RedisUtil;
import com.github.wuchao.webproject.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.util.Pool;

import java.util.List;

@Getter
@Setter
@Service
@Slf4j
public class JetCacheService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Pool pool;

    /**
     * String 表示 key 的类型
     * User 表示缓存数据类型
     */
    @CreateCache(expire = 600)
    public Cache<String, User> userCache;

//    @PostConstruct
//    public void init() {
//        try {
//            Method method = JetCacheService.class.getMethod("getCacheUser", new Class[]{String.class});
//            String keyPrefix = RedisUtil.keyGenerator(JetCacheService.class.getName(), method.getName(), method.getParameterTypes());
//            JetCacheService.userCache = RedisCacheBuilder.createRedisCacheBuilder()
//                    .jedisPool(pool)
//                    .keyPrefix(keyPrefix)
//                    .expireAfterWrite(60, TimeUnit.SECONDS)
//                    .buildCache();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        log.info("-------------- CacheBuilder 创建 Cache 完成 --------------");
//    }

    public User getCachedUser(String username) {
        log.info("----------------------------- Caffeine 调用方法 -----------------------------");
        return userRepository.findByUsername(username);
    }

    public User getUser(String username) {
        // GET、GET_ALL 这类大写 API 只纯粹访问缓存，不会调用 loader
        CacheGetResult<User> r = userCache.GET(username);
        if (r.isSuccess()) {
            return r.getValue();
        } else {
            throw new RuntimeException("cache result status: " + r.getResultCode());
        }
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
