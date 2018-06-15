package com.github.wuchao.webproject.service;

import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Slf4j
@CacheConfig(cacheNames = "users")
public class RedisService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @Cacheable
//    @Transactional
    public User getUser(String username) {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        if (CollectionUtils.isNotEmpty(cacheNames)) {
            cacheNames.stream().forEach(s -> log.info("缓存：name= {}", s));
        }

        User user = userRepository.findByUsername(username);
        return user;
    }

}
