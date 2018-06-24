package com.github.wuchao.webproject.common;

import com.github.wuchao.webproject.redis.CachedMethodInvocation;
import com.github.wuchao.webproject.redis.RedisLock;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class Constants {

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATTER = "yyyy-MM-dd";

    public static final Map<String, CachedMethodInvocation> REDIS_CACHE_METHOD_INVOCATION_MAP = new HashMap();
    public static final Map<String, RedisLock> REDIS_LOCK_MAP = new HashMap<>();

    /**
     * 全局线程局
     */
    public static ExecutorService GLOBAL_THREAD_POOL = new ThreadPoolExecutor(5, 500, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1024), new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").build());

    private Constants() {
        throw new RuntimeException();
    }

}
