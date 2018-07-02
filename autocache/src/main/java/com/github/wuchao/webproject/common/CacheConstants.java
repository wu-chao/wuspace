package com.github.wuchao.webproject.common;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;

import java.util.HashMap;
import java.util.Map;

public final class CacheConstants {

    public static Map<String, AsyncLoadingCache> CAFFEINE_CACHE_MAP = new HashMap<>();

}
