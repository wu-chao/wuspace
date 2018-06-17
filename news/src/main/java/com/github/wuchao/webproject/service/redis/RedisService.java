package com.github.wuchao.webproject.service.redis;

import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "users")
public class RedisService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisUtil redisUtil;

    //    @Cacheable
//    @Transactional
    public User getUser(String username) {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        if (CollectionUtils.isNotEmpty(cacheNames)) {
            cacheNames.stream().forEach(s -> log.info("缓存：name= {}", s));
        }
        String key = "key1122";
        User user = redisUtil.get(key, User.class);
        if (user == null) {
            user = userRepository.findByUsername(username);
            redisUtil.set(key, user);
        }

        /**
         * ((RedisCacheManager) cacheManager).getRedisOperations().opsForValue().get("key112")
         */

        return user;
    }


    public List<User> getUsers() {
        String key = "key22222233456";
        List<User> users = redisUtil.getList(key, User.class);
        if (users == null) {
            users = userRepository.findAll();
            redisUtil.setList(key, users);
        }
        return users;
    }

}
