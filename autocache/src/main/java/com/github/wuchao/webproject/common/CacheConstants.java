package com.github.wuchao.webproject.common;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class CacheConstants {

    /**
     * 全局线程局
     */
    public static ExecutorService GLOBAL_THREAD_POOL = new ThreadPoolExecutor(5, 500, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1024), new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").build());

    /**
     * 缓存线程池
     */
    public static ExecutorService CACHE_THREAD_POOL = new ThreadPoolExecutor(5, 300, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1024), new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").build());

    public static Map<String, AsyncLoadingCache> CAFFEINE_CACHE_MAP = new HashMap<>();

}
