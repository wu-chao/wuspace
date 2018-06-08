package com.github.wuchao.webproject.aop;

import com.github.wuchao.webproject.springcacherefresh2.CachingAnnotationsAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 */
@Component
@Aspect
@Slf4j
public class RedisCacheAspect {

    @Autowired
    CachingAnnotationsAspect cachingAnnotationsAspect;

    @Pointcut("execution(public * com.github.wuchao.webproject.repository.*.*(..))")
    public void methodsToBeInspected() {
    }

    @Around("methodsToBeInspected()")
    public Object interceptCaches(ProceedingJoinPoint joinPoint) throws Throwable {
        return cachingAnnotationsAspect.interceptCacheables(joinPoint);
    }

}
