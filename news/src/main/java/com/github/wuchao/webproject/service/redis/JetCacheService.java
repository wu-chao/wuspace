package com.github.wuchao.webproject.service.redis;

import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.redis.RedisUtil;
import com.github.wuchao.webproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JetCacheService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtil redisUtil;

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
