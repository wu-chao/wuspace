package com.wuspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by WUCHAO on 2017/2/20.
 */
@SpringBootApplication
public class QuartzApplication {

    public static void main(String args[]) {
        ConfigurableApplicationContext context = SpringApplication.run(QuartzApplication.class, args);

    }
}
