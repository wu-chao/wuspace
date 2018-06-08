package com.github.wuchao.webproject.redis;


import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRedis() {
        for (int i = 0; i < 10; i++) {
            Optional<User> userOptional = userRepository.findOneByUsername("admin");
            if (userOptional.isPresent()) {
                log.info("第{}次执行查询结果：" + userOptional.get().getUsername(), i);
            }
        }
    }

}
