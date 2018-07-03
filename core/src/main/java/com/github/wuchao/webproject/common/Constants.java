package com.github.wuchao.webproject.common;

import com.github.wuchao.webproject.redis.CachedMethodInvocation;
import com.github.wuchao.webproject.redis.RedisLock;

import java.util.HashMap;
import java.util.Map;

public final class Constants {

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATTER = "yyyy-MM-dd";

    public static final Map<String, CachedMethodInvocation> REDIS_CACHE_METHOD_INVOCATION_MAP = new HashMap();
    public static final Map<String, RedisLock> REDIS_LOCK_MAP = new HashMap<>();

    private Constants() {
        throw new RuntimeException();
    }

}
