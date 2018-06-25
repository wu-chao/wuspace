package com.github.wuchao.webproject.redis;

import com.github.wuchao.webproject.common.Constants;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.repository.UserRepository;
import com.github.wuchao.webproject.util.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.MethodInvoker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 手动刷新缓存实现类
 */
@Component
@Slf4j
public class CacheSupportImpl implements CacheSupport {

    @Autowired
    DefaultListableBeanFactory beanFactory;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void refreshAllCaches() {
        Map<String, CachedMethodInvocation> methodInvocations = Constants.REDIS_CACHE_METHOD_INVOCATION_MAP;
        if (MapUtils.isNotEmpty(methodInvocations)) {
            String[] key = {""};
            CachedMethodInvocation[] value = new CachedMethodInvocation[1];
            methodInvocations.entrySet().forEach(methodInvocation -> {
                key[0] = methodInvocation.getKey();
                value[0] = methodInvocation.getValue();
                if (StringUtils.isNotBlank(key[0])) {
                    Integer schedule = Integer.valueOf(key[0].substring(key[0].lastIndexOf("_") + 1));
                    if (key[0].contains("getUser")) {
                        List<User> users = userRepository.findAll();
                        if (CollectionUtils.isNotEmpty(users)) {
                            users.forEach(user -> {
                                String[] cacheKey = {key[0].substring(0, key[0].lastIndexOf("_")) + user.getUsername() + "_" + schedule};
                                value[0].setArguments(new ArrayList() {{
                                    add(user.getUsername());
                                }});
                                ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
                                service.scheduleWithFixedDelay(() ->
                                        refreshCache(value[0], cacheKey[0]), 0, schedule, TimeUnit.SECONDS);
                            });
                        }

                    }
                }
            });
        }
    }

    private void refreshCache(CachedMethodInvocation invocation, String key) {
        RedisLock redisLock = new RedisLock(redisTemplate, key + "_lock");
        Class methodReturnClass = invocation.getMethodReturnClass();
        if (methodReturnClass == List.class) {
            redisLock.setData(redisUtil.getListWithoutLock(key, methodReturnClass));
        } else {
            redisLock.setData(redisUtil.getWithoutLock(key, methodReturnClass));
        }
        Constants.REDIS_LOCK_MAP.put(key + "_lock", redisLock);

        try {
            /**
             * 更新缓存前将缓存放在RedisLock中，查询缓存时判断lock的状态，如果已锁定，就从lock里面取临时数据，
             * 这里更新完缓存后unlock，然后清除lock中保存的临时数据
             */
            if (redisLock.lock()) {
                log.info(redisLock.getLockKeyLog() + " 已锁定，将加载旧数据\n");
                Thread.sleep(5);

                // 通过代理调用方法，并记录返回值
                Object computed = invoke(invocation);

                // 通过Cache对象更新缓存
                redisUtil.set(key, computed);

                // 刷新redis中缓存法信息key的有效时间
//                redisTemplate.expire(key, Long.valueOf(key.substring(key.lastIndexOf("_") + 1)), TimeUnit.SECONDS);
                redisTemplate.expire(key, 60, TimeUnit.SECONDS);

                log.info("缓存：{}-{}，重新加载数据", key, key.getBytes());
            }

        } catch (Exception e) {

            log.info("刷新缓存失败：" + e.getMessage(), e);
        } finally {

            redisLock.unlock();
        }

    }

    private Object invoke(CachedMethodInvocation invocation) throws Exception {
        // 获取执行方法所需要的参数
        Object[] args = null;
        if (!CollectionUtils.isEmpty(invocation.getArguments())) {
            args = invocation.getArguments().toArray();
        }
        // 通过先获取Spring的代理对象，在根据这个对象获取真实的实例对象
        Object target = ReflectionUtils.getTarget(beanFactory.getBean(Class.forName(invocation.getTargetBean())));

        final MethodInvoker invoker = new MethodInvoker();
        invoker.setTargetObject(target);
        invoker.setArguments(args);
        invoker.setTargetMethod(invocation.getTargetMethod());
        invoker.prepare();

        return invoker.invoke();
    }

}
