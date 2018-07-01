package com.github.wuchao.webproject.runner;

import com.github.wuchao.webproject.redis.CacheSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order
@Slf4j
public class TimingCacheSchedule implements CommandLineRunner {

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public void run(String... args) {
        CacheSupport cacheSupport = beanFactory.getBean(CacheSupport.class);
        cacheSupport.invokeAllMethods();
    }

}
