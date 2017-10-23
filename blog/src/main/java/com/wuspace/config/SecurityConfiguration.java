package com.wuspace.config;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration(value = "blogSecurityConfiguration")
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER - 1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationManagerBuilder authenticationManagerBuilder;

    private UserDetailsService userDetailsService;

    /**
     * @EnableAutoConfiguration作用： Spring Boot 会自动根据你 jar 包的依赖来自动配置项目。例如当你项目下面有HSQLDB的依赖时，
     * Spring Boot 会创建默认的内存数据库的数据源DataSource，如果你自己创建了DataSource，
     * Spring Boot 就不会创建默认的DataSource。
     */
    @Autowired
    private DataSource dataSource;

    @Autowired
    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder,
                                 UserDetailsService userDetailsService) {

        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    public void init() {
        try {
            authenticationManagerBuilder
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/bower_components", "/bower_components/jquery/dist/**", "/bower_components/bootstrap/dist/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
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
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities where username = ?")
        ;
    }

    /**
     * http://blog.csdn.net/w605283073/article/details/51322771
     */
    @Component
    public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        private String targetUrl = "localhost:8080/blogs";

        @Override
        protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            String targetUrl = determineTargetUrl(request, response);
            if (response.isCommitted()) {
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
}
