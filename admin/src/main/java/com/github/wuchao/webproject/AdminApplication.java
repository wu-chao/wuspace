package com.github.wuchao.webproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.wuspace\\.controller\\.app\\..*"))
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
public class AdminApplication {
    public static void main(String args[]) {
        SpringApplication.run(AdminApplication.class);
    }
}