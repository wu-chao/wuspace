package com.github.wuchao.webproject.springcacherefresh2;

import com.github.wuchao.webproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableScheduling
@EnableLoadTimeWeaving
@Slf4j
public class SpringCacheRefreshTests {

    private static final String CACHE_NAME = "default";

//    @Autowired
//    BusinessService businessService;
//
//    @Autowired
//    UnstableBusinessService unstableService;

    @Autowired
    CacheOperations cacheOperations;

    @Autowired
    RedisCacheManager cacheManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRedisRefreshCache() {
        userRepository.findOneByUsername("admin").ifPresent(user -> log.info("user0:" + user.getUsername()));

        cacheOperations.refreshCache(CACHE_NAME);

        int indexs[] = {0};
        for (int i = 0; ; ) {
            i++;
            indexs[0] = i;
            userRepository.findOneByUsername("admin").ifPresent(user -> log.info("user{}:{}", indexs[0], user.getUsername()));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Tests standard Spring cache to validate the setup
     */
//    @Test
//    public void testStandardCacheSimple() {
//        String response1 = businessService.business("param1", "param2");
//        String response2 = businessService.business("param1", "param2");
//        assertEquals(response2, response1);
//    }

    /**
     * Tests standard Spring cache negative case to validate the setup is good
     * for testing reloads
     */
//    @Test
//    public void testStandardCacheNegative() {
//        String response1 = businessService.business("param1", "param2");
//        cacheManager.getCache(CACHE_NAME).clear();
//        String response2 = businessService.business("param1", "param2");
//        assertNotEquals(response2, response1);
//    }

    /**
     * Tests cached value refresh across cache invocations.
     */
//    @Test
//    public void testCacheReloadPositive() {
//        String response1 = businessService.business("param1", "param2");
//        cacheOperations.refreshCache(CACHE_NAME);
//        String response2 = businessService.business("param1", "param2");
//        System.out.println("\n" + response1 + "==" + response2 + "\n");
//        assertEquals(response2, response1);
//    }

    /**
     * Tests cached value retention when business service fails during refresh.
     */
//    @Test
//    public void testCacheReloadWithError() {
//        String response1 = unstableService.business("paramx", "paramy");
//        unstableService.setDown(true);
//        cacheOperations.refreshCache(CACHE_NAME);
//        String response2 = unstableService.business("paramx", "paramy");
//        assertEquals(response2, response1);
//    }

}

/**
 * Any Business service that uses @Cacheable
 *
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 * <p>
 * An Unstable service that may go down and start throwing exceptions.
 * @author szaidi
 * <p>
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 * @author Saiyed Zaidi
 */
//@Component("businessService")
//class BusinessServiceImpl implements BusinessService {
//    @Cacheable(value = "default")
//    public String business(String param1, String param2) {
//        String result = "output " + new Random().nextInt();
//        return result;
//    }
//}

//interface BusinessService {
//    String business(String param1, String param2);
//}
//
//@Component("unstableService")
//class UnstableBusinessServiceImpl implements UnstableBusinessService {
//
//    boolean down = false;
//
//    public void setDown(boolean down) {
//        this.down = down;
//    }
//
//    @Cacheable(value = "default")
//    public String business(String param1, String param2) {
//        if (down) {
//            throw new RuntimeException("Service down");
//        }
//        return "output " + new Random().nextInt();
//    }
//}

/**
 * An Unstable service that may go down and start throwing exceptions.
 *
 * @author szaidi
 */
//interface UnstableBusinessService {
//    /**
//     * Business as usual
//     *
//     * @param param1
//     * @param param2
//     * @return
//     */
//    String business(String param1, String param2);
//
//    /**
//     * Set service down status
//     *
//     * @param down
//     */
//    void setDown(boolean down);
//}

/**
 * Advice to allow watching the Business service invocations. Passes the
 * invocations to CachingAnnotationsAspect
 *
 * @author Saiyed Zaidi
 */
//@Aspect
//@Component
//class TestAdvice {
//
//    @Pointcut("execution(public * com.github.wuchao.webproject.springcacherefresh2.BusinessService.*(..))")
//    public void methodsToBeInspected() {
//    }
//
//    @Autowired
//    CachingAnnotationsAspect cachingAnnotationsAspect;
//
//    @Around("methodsToBeInspected()")
//    public Object interceptCaches(ProceedingJoinPoint joinPoint) throws Throwable {
//        return cachingAnnotationsAspect.interceptCacheables(joinPoint);
//    }
//}