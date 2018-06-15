package com.github.wuchao.webproject.redis;

import com.github.wuchao.webproject.NewsApplication;
import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewsApplication.class)
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
            log.info("第{}次执行查询结果：" + user.equals(user1));
        }

        /**
         result:
         2018-06-14 15:40:50.142  INFO 13620 --- [           main] c.g.w.w.config.RedisCacheConfig          : 调用Redis缓存:Key= com.sun.proxy.$Proxy172.findOneByUsername(admin)
         Hibernate: select user0_.id as id1_11_0_, authority2_.name as name1_0_1_, user0_.created_by as created_2_11_0_, user0_.created_date as created_3_11_0_, user0_.deleted as deleted4_11_0_, user0_.last_modified_by as last_mod5_11_0_, user0_.last_modified_date as last_mod6_11_0_, user0_.activated as activate7_11_0_, user0_.avatar_url as avatar_u8_11_0_, user0_.description as descript9_11_0_, user0_.email as email10_11_0_, user0_.nickname as nicknam11_11_0_, user0_.password_hash as passwor12_11_0_, user0_.phone as phone13_11_0_, user0_.username as usernam14_11_0_, authority2_.value as value2_0_1_, authoritie1_.user_id as user_id1_12_0__, authoritie1_.authority_name as authorit2_12_0__ from user user0_ left outer join user_authority authoritie1_ on user0_.id=authoritie1_.user_id left outer join authority authority2_ on authoritie1_.authority_name=authority2_.name where user0_.username=?
         2018-06-14 15:40:50.673  INFO 13620 --- [           main] c.g.wuchao.webproject.redis.RedisTests   : 第1次执行查询结果：admin
         2018-06-14 15:40:50.778  INFO 13620 --- [           main] c.g.wuchao.webproject.redis.RedisTests   : 第2次执行查询结果：admin
         2018-06-14 15:40:50.781  INFO 13620 --- [           main] c.g.wuchao.webproject.redis.RedisTests   : 第3次执行查询结果：admin
         2018-06-14 15:40:50.783  INFO 13620 --- [           main] c.g.wuchao.webproject.redis.RedisTests   : 第4次执行查询结果：admin
         2018-06-14 15:40:50.785  INFO 13620 --- [           main] c.g.wuchao.webproject.redis.RedisTests   : 第5次执行查询结果：admin
         2018-06-14 15:40:50.786  INFO 13620 --- [           main] c.g.wuchao.webproject.redis.RedisTests   : 第6次执行查询结果：admin
         2018-06-14 15:40:50.789  INFO 13620 --- [           main] c.g.wuchao.webproject.redis.RedisTests   : 第7次执行查询结果：admin
         2018-06-14 15:40:50.790  INFO 13620 --- [           main] c.g.wuchao.webproject.redis.RedisTests   : 第8次执行查询结果：admin
         2018-06-14 15:40:50.791  INFO 13620 --- [           main] c.g.wuchao.webproject.redis.RedisTests   : 第9次执行查询结果：admin
         2018-06-14 15:40:50.792  INFO 13620 --- [           main] c.g.wuchao.webproject.redis.RedisTests   : 第10次执行查询结果：admin
         */
    }

}
