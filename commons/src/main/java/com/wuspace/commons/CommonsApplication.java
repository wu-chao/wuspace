package com.wuspace.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootConfiguration
@EnableAspectJAutoProxy
public class CommonsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonsApplication.class);
    }
}
