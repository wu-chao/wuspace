package com.github.wuchao.webproject.redis;

/**
 * 注册和刷新缓存接口
 */
public interface CacheSupport {

    void refreshAllCaches();

    void invokeAllMethods();

}