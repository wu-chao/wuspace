package com.github.wuchao.webproject.redis;

import com.github.wuchao.webproject.CoreApplication;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
@WebAppConfiguration
@Slf4j
public class RedisTests {

    @Autowired
    private RedisService redisService;

    @Test
    public void testRedis() {
        int[] index = {0};
        for (int i = 0; i < 10; i++) {
            index[0] = i + 1;
            User user = redisService.getUser("admin");
            User user1 = redisService.getUser("admin");
            log.info("第{}次执行查询结果：" + (user == user1));
        }

    }

}
