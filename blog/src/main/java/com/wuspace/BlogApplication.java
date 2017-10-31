package com.wuspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
@EnableDiscoveryClient
public class BlogApplication {
    public static void main(String args[]) {
        ConfigurableApplicationContext context = SpringApplication.run(BlogApplication.class, args);
    }
}
