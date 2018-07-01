package com.github.wuchao.webproject.redis;

import com.github.wuchao.webproject.util.ReflectionUtils;
import com.github.wuchao.webproject.util.SpringContextUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.MethodInvoker;

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
    private List<Object> arguments = new ArrayList<>();
    private List<String> parameterTypes = new ArrayList<>();
    private Class methodReturnClass;
    private RedisLock redisLock = null;

    public CachedMethodInvocation() {
    }

    public CachedMethodInvocation(String key, String targetBean, Method targetMethod, Class[] parameterTypes, Object[] arguments, Class methodReturnClass, RedisLock redisLock) {
        this.key = key;
        this.targetBean = targetBean;
        this.targetMethod = targetMethod.getName();
        if (arguments != null && arguments.length != 0) {
            this.arguments = Arrays.asList(arguments);
        }
        if (parameterTypes != null && parameterTypes.length != 0) {
            for (Class clazz : parameterTypes) {
                this.parameterTypes.add(clazz.getName());
            }
        }
        this.methodReturnClass = methodReturnClass;
        this.redisLock = redisLock;
    }

    public CachedMethodInvocation(String key, String targetBean, Method targetMethod, Class[] parameterTypes, Object[] arguments) {
        this(key, targetBean, targetMethod, parameterTypes, arguments, null, null);
    }

    public CachedMethodInvocation(String key, String targetBean, Method targetMethod, Class[] parameterTypes, Class methodReturnClass) {
        this(key, targetBean, targetMethod, parameterTypes, null, methodReturnClass, null);
    }

    public Object invoke() throws Exception {
        // 获取执行方法所需要的参数
        Object[] args = null;
        if (!CollectionUtils.isEmpty(this.getArguments())) {
            args = this.getArguments().toArray();
        }
        // 通过先获取Spring的代理对象，在根据这个对象获取真实的实例对象
        Object target = ReflectionUtils.getTarget(SpringContextUtil.getBean(Class.forName(this.getTargetBean())));

        final MethodInvoker invoker = new MethodInvoker();
        invoker.setTargetObject(target);
        invoker.setArguments(args);
        invoker.setTargetMethod(this.getTargetMethod());
        invoker.prepare();

        return invoker.invoke();
    }

    public String getKey() {
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

    public Class getMethodReturnClass() {
        return methodReturnClass;
    }

    public void setMethodReturnClass(Class methodReturnClass) {
        this.methodReturnClass = methodReturnClass;
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
        return Objects.equals(targetBean, that.targetBean) &&
                Objects.equals(targetMethod, that.targetMethod) &&
                Objects.equals(arguments, that.arguments) &&
                Objects.equals(parameterTypes, that.parameterTypes) &&
                Objects.equals(redisLock, that.redisLock);
    }

    @Override
    public int hashCode() {

        return Objects.hash(targetBean, targetMethod, arguments, parameterTypes, redisLock);
    }
}

