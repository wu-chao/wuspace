package com.github.wuchao.webproject.config;

import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class QuerydslConfig {

//    @Bean
//    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
//        return new JPAQueryFactory(new HQLTemplates(), entityManager);
//    }

}
