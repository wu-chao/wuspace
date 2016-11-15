package com.wuspace.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by WUCHAO on 2016/11/16.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
//                .antMatchers("/menu").permitAll()
//                .antMatchers("/error").permitAll()
                .antMatchers("/resources/**").permitAll()
//                .antMatchers("/css/**").permitAll()
//                .antMatchers("/js/**").permitAll()
//                .antMatchers("/fonts/**").permitAll()
//                .antMatchers("/libs/**").permitAll()
//                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/users/login")
                .permitAll();

    }
}
