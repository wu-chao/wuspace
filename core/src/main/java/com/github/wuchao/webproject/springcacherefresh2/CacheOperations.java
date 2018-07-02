package com.github.wuchao.webproject.springcacherefresh2;

import java.util.Collection;

/**
 * Provides methods to refresh cached objects.
 *
 * @author szaidi
 * @version 1.0
 * @copyright @2016 http://yantrashala.github.io
 */
public interface CacheOperations {

    /**
     * Returns all the configured cache names.
     *
     * @return
     */
    Collection<String> getCacheNames();

    /**
     * Refreshes caches corresponding to the supplied cache name.
     *
     * @param cacheName
     */
    void refreshCache(String cacheName);

    /**
     * Refreshes caches corresponding to the supplied cache names array.
     *
     * @param cacheNames
     */
    void refreshCaches(String... cacheNames);

    /**
     * Refreshes all caches configured in the application
     */
    void refreshAllCaches();

    /**
     * Clears all values from the named caches
     *
     * @param cacheNames
     */
    void evictCache(String... cacheNames);

    /**
     * Clears all values from the named cache
     *
     * @param cacheName
     */
    void evictCache(String cacheName);
}