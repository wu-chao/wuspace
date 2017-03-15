package com.wuspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class QuartzApplication {

    public static void main(String args[]) {
        ConfigurableApplicationContext context = SpringApplication.run(QuartzApplication.class, args);
    }
}