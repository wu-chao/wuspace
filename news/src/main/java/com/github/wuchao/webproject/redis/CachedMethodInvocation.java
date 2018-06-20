package com.github.wuchao.webproject.redis;

import com.github.wuchao.webproject.config.redis.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 标记了缓存注解的方法类信息
 * 用于主动刷新缓存时调用原始方法加载数据
 */
@Component
public final class CachedMethodInvocation {

    private String key;
    private String targetBean;
    private String targetMethod;
    private List<Object> arguments;
    private List<String> parameterTypes = new ArrayList<>();
    private RedisLock redisLock = null;

    @Autowired
    private RedisTemplate redisTemplate;

    public CachedMethodInvocation() {
    }

    public CachedMethodInvocation(String key, Object targetBean, Method targetMethod, Class[] parameterTypes, Object[] arguments) {
        this.key = key;
        this.targetBean = targetBean.getClass().getName();
        this.targetMethod = targetMethod.getName();
        if (arguments != null && arguments.length != 0) {
            this.arguments = Arrays.asList(arguments);
        }
        if (parameterTypes != null && parameterTypes.length != 0) {
            for (Class clazz : parameterTypes) {
                this.parameterTypes.add(clazz.getName());
            }
        }
    }

    public void refreshCache() {
        this.redisLock = new RedisLock(redisTemplate, key);

    }

    public Object getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTargetBean() {
        return targetBean;
    }

    public void setTargetBean(String targetBean) {
        this.targetBean = targetBean;
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    public List<Object> getArguments() {
        return arguments;
    }

    public void setArguments(List<Object> arguments) {
        this.arguments = arguments;
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(List<String> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public RedisLock getRedisLock() {
        return redisLock;
    }

    public void setRedisLock(RedisLock redisLock) {
        this.redisLock = redisLock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CachedMethodInvocation that = (CachedMethodInvocation) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(targetBean, that.targetBean) &&
                Objects.equals(targetMethod, that.targetMethod) &&
                Objects.equals(arguments, that.arguments) &&
                Objects.equals(parameterTypes, that.parameterTypes) &&
                Objects.equals(redisLock, that.redisLock);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key, targetBean, targetMethod, arguments, parameterTypes, redisLock);
    }
}

