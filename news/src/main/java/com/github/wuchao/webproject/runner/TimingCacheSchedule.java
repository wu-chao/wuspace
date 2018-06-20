package com.github.wuchao.webproject.runner;

import com.github.wuchao.webproject.common.Constants;
import com.github.wuchao.webproject.redis.CachedMethodInvocation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Order
@Slf4j
public class TimingCacheSchedule implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }

//    @Override
//    public void run(String... args) {
//        if (Constants.TIMING_CACHE_MAP != null) {
//
//            Constants.TIMING_CACHE_MAP.entrySet().forEach(cachedMethodInvocationEntry -> {
//                String key = cachedMethodInvocationEntry.getKey();
//                CustomizedRedisCache cache = cachedMethodInvocationEntry.getValue();
//                if (StringUtils.isNotBlank(key)) {
//                    Integer schedule = Integer.valueOf(key.substring(key.lastIndexOf("_") + 1));
//                    ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
//                    log.info("\n---------------------执行定时任务------------------------\n");
//                    service.scheduleWithFixedDelay(() ->
//                            cache.refreshCache(key, cache.getCacheKey(key)), 0, schedule, TimeUnit.SECONDS);
//                }
//            });
//        }
//    }

    public void refreshCaches() {
        Map<String, CachedMethodInvocation> methodInvocations = Constants.REDIS_CACHE_METHOD_INVOCATION_MAP;
        if (MapUtils.isNotEmpty(methodInvocations)) {
            String[] key = {""};
            CachedMethodInvocation[] value = new CachedMethodInvocation[1];
            methodInvocations.entrySet().forEach(methodInvocation -> {
                key[0] = methodInvocation.getKey();
                value[0] = methodInvocation.getValue();
                if (StringUtils.isNotBlank(key[0])) {
                    Integer schedule = Integer.valueOf(key[0].substring(key[0].lastIndexOf("_") + 1));
                    ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
                    service.scheduleWithFixedDelay(() ->
                            cache.refreshCache(key, cache.getCacheKey(key)), 0, schedule, TimeUnit.SECONDS);
                }
            });
        }
    }
}
