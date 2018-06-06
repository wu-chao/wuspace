package com.github.wuchao.webproject.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class HibernateConfig {

//    @Bean
//    public SessionFactory sessionFactory(EntityManagerFactory entityManagerFactory) {
//        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
//            throw new NullPointerException("factory is not a hibernate factory");
//        }
//        return entityManagerFactory.unwrap(SessionFactory.class);
//    }
}
