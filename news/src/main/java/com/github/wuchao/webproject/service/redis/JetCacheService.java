package com.github.wuchao.webproject.service.redis;

import com.github.wuchao.webproject.common.Constants;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.redis.CachedMethodInvocation;
import com.github.wuchao.webproject.redis.RedisUtil;
import com.github.wuchao.webproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class JetCacheService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        try {
            String key = redisUtil.keyGenerator(this.getClass().getName(),
                    "getUser", null, 30);
            CachedMethodInvocation cachedMethodInvocation = new CachedMethodInvocation(key, JetCacheService.class.getName(),
                    JetCacheService.class.getMethod("invokeMethod", String.class), new Class[]{String.class}, Void.class);
            Constants.REDIS_CACHE_METHOD_INVOCATION_MAP.put(key, cachedMethodInvocation);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void invokeMethod(String username) {
        log.info("----------------------------- Caffeine 调用方法 -----------------------------");
        userRepository.findByUsername(username);
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
