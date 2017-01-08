package com.wuspace.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by WUCHAO on 2016/11/16.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = new CustomAuthenticationSuccessHandler();

    private CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //mvcMatcher("");区别
                .antMatchers("/").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                //.failureUrl("/")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                //.logoutSuccessUrl("")
                .permitAll()
                .and()
                .csrf()
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                /*token-validity-seconds：The expire date of “remember-me” cookie, in seconds*/
                .tokenValiditySeconds(7*24*60*60);

    }

    /**
     * https://stackoverflow.com/questions/35218354/difference-between-registerglobal-configure-configureglobal-configureglo
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                //.inMemoryAuthentication().withUser("chao").password("password").roles("USER")
                .userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * http://blog.csdn.net/w605283073/article/details/51322771
     */
    @Component
    public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        private String targetUrl;

        public CustomAuthenticationSuccessHandler() {}

        public CustomAuthenticationSuccessHandler(String targetUrl) {
            this.targetUrl = targetUrl;
        }

        @Override
        protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            String targetUrl = determineTargetUrl(request, response);
            if(response.isCommitted()) {
                this.logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            } else {
                this.redirectStrategy.sendRedirect(request, response, targetUrl);
            }
        }

        @Override
        protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {

            return targetUrl;
        }
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        /*If this is specified, “Persistent Token Approach” will be used. Defaults to “Simple Hash-Based Token Approach”*/
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Component
    public class CustomUserDetailsService implements UserDetailsService {

        @Override
        public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            return null;
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
}
