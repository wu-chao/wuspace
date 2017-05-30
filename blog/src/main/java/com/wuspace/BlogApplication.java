package com.wuspace;

import com.wuspace.domain.security.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by WUCHAO on 2016/10/14.
 */
@SpringBootApplication
public class BlogApplication {

    public static void main(String args[]) {
        //SpringApplication.run(WuspaceManagerApplication.class, args);
        //测试内存数据库
        ConfigurableApplicationContext context = SpringApplication.run(BlogApplication.class, args);
        UserRepository userRepository = context.getBean(UserRepository.class);
        long count = userRepository.count();
        System.out.println("---------------" + count + "--------------");
    }
}
