package com.github.wuchao.webproject;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.RefreshPolicy;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.repository.UserRepository;
import com.github.wuchao.webproject.service.redis.JetCacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JetCacheRunner {

    @Autowired
    private JetCacheService jetCacheService;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        Cache<String, User> userCache = jetCacheService.getUserCache();
        // 直接 jetCacheService.userCache 结果为 null ？？？
        if (userCache != null) {
            userCache.config().setLoader(username -> jetCacheService.getCachedUser(username));
            userCache.config().setRefreshPolicy(RefreshPolicy.newPolicy(600, TimeUnit.SECONDS));
            List<String> names = userRepository.findAll().stream().map(user -> user.getUsername()).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(names)) {
                // 用 get 方法取缓存，没有命中的话会调用 loader 去查询数据库
                names.forEach(userCache::get);
            }
        }
    }

}
