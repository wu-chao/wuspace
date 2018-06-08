package com.github.wuchao.webproject.springcacherefresh2;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Records invocations of methods with @Cacheable annotations. Uses the
 * invocations to refresh the cached values
 *
 * @author Saiyed Zaidi
 * @version 1.0
 * @copyright @2016 http://yantrashala.github.io
 */
public interface InvocationRegistry {

    /**
     * Records invocations of methods with @Cacheable annotations
     *
     * @param invokedBean
     * @param invokedMethod
     * @param invocationArguments
     * @param cacheNames
     */
    void registerInvocation(Object invokedBean, Method invokedMethod, Object[] invocationArguments, Set<String> cacheNames);

}