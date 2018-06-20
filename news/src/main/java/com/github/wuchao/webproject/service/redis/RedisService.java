package com.github.wuchao.webproject.service.redis;

import com.github.wuchao.webproject.common.Constants;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.redis.CachedMethodInvocation;
import com.github.wuchao.webproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Service
@Slf4j
//@CacheConfig(cacheNames = "users")
public class RedisService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    public User getUser(String username) throws NoSuchMethodException {
        String key = RedisUtil.keyGenerator(this.getClass().getName(),
                "getUser", new String[]{username}, 30);
        CachedMethodInvocation cachedMethodInvocation = new CachedMethodInvocation(key, this.getClass().getName(),
                this.getClass().getMethod("getUser", String.class), new Class[]{String.class}, new String[]{username});
        Constants.REDIS_CACHE_METHOD_INVOCATION_MAP.put(key, cachedMethodInvocation);
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

        User user = redisUtil.get(key, User.class);
        if (user == null) {
            user = userRepository.findByUsername(username);
            redisUtil.setWithExpiration(key, user, 20);
        }

        return user;
    }

    public User getUser2(String username) {
        String key = RedisUtil.keyGenerator(this.getClass().getName(),
                "getUser2", new String[]{username}, 0);
        User user = redisUtil.get(key, User.class);
        if (user == null) {
            user = userRepository.findByUsername(username);
        }

        return user;
    }


    public List<User> getUsers() {
        String key = RedisUtil.keyGenerator(this.getClass().getName(),
                "getUsers", new Object[]{}, 0);
        List<User> users = redisUtil.getList(key, User.class);
        if (users == null) {
            users = userRepository.findAll();
            redisUtil.set(key, users);
        }
        return users;
    }

}
