package com.github.wuchao.webproject.service.redis;

import com.github.wuchao.webproject.common.Constants;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.redis.CachedMethodInvocation;
import com.github.wuchao.webproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.function.Function;

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
    private com.github.wuchao.webproject.redis.RedisUtil redisUtil;

    @PostConstruct
    public void init(Function username) {
        String key = redisUtil.keyGenerator(this.getClass().getName(),
                "getUser", new String[]{username}, 30);

        User user = userRepository.findByUsername(username);
        redisUtil.set(key, user);
    }

    public User getUser(String username) {
        String key = redisUtil.keyGenerator(this.getClass().getName(),
                "getUser", new String[]{username}, 30);
        User user = redisUtil.get(key, User.class);
        return user;
    }

    @PostConstruct
    public void addScheduledTask() throws NoSuchMethodException {
        String key = redisUtil.keyGenerator(this.getClass().getName(),
                "getUser", new String[]{"user1"}, 30);
        CachedMethodInvocation cachedMethodInvocation = new CachedMethodInvocation(key, this.getClass().getName(),
                this.getClass().getMethod("getUser", String.class), new Class[]{String.class}, new String[]{"user1"});
        Constants.REDIS_CACHE_METHOD_INVOCATION_MAP.put(key, cachedMethodInvocation);
    }

    public User getUser2(String username) {
        String key = redisUtil.keyGenerator(this.getClass().getName(),
                "getUser2", new String[]{username}, 0);
        User user = redisUtil.get(key, User.class);
        if (user == null) {
            user = userRepository.findByUsername(username);
        }

        return user;
    }


    public List<User> getUsers() {
        String key = redisUtil.keyGenerator(this.getClass().getName(),
                "getUsers", new Object[]{}, 0);
        List<User> users = redisUtil.getList(key, User.class);
        if (users == null) {
            users = userRepository.findAll();
            redisUtil.set(key, users);
        }
        return users;
    }

}
