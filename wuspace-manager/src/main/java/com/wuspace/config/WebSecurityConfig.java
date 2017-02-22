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
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * @EnableAutoConfiguration作用：
     * Spring Boot 会自动根据你 jar 包的依赖来自动配置项目。例如当你项目下面有HSQLDB的依赖时，
     * Spring Boot 会创建默认的内存数据库的数据源DataSource，如果你自己创建了DataSource，
     * Spring Boot 就不会创建默认的DataSource。
     */
    @Autowired
    private DataSource dataSource;

//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;

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
                //.successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                //.logoutSuccessUrl("")
                .permitAll()
                .and()
                .csrf()
                //.and()
                //.rememberMe()
                //.tokenRepository(persistentTokenRepository())
                /*token-validity-seconds：The expire date of “remember-me” cookie, in seconds*/
                //.tokenValiditySeconds(7*24*60*60)
                ;

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
                //1.使用
                .inMemoryAuthentication()
                .withUser("chao")
                .password("password")
                .roles("USER")
                .authorities("/xxx/**");
                /**
                 * http://www.mkyong.com/spring-security/spring-security-form-login-using-database/
                 */
                //2.使用 jdbc-user-service（XML中的名称）:
//              .jdbcAuthentication().dataSource(dataSource)
                        /*DaoAuthenticationProvider:
                            createUserSql = "insert into users (username, password, enabled) values (?,?,?)"
                            deleteUserSql = "delete from users where username = ?"
                            updateUserSql = "update users set password = ?, enabled = ? where username = ?"
                            createAuthoritySql = "insert into authorities (username, authority) values (?,?)"
                            deleteUserAuthoritiesSql = "delete from authorities where username = ?"
                            userExistsSql = "select username from users where username = ?"
                            changePasswordSql = "update users set password = ? where username = ?"
                            findAllGroupsSql = "select group_name from groups"
                            findUsersInGroupSql = "select username from group_members gm, groups g where gm.group_id = g.id and g.group_name = ?"
                            insertGroupSql = "insert into groups (group_name) values (?)"
                            findGroupIdSql = "select id from groups where group_name = ?"
                            insertGroupAuthoritySql = "insert into group_authorities (group_id, authority) values (?,?)"
                            deleteGroupSql = "delete from groups where id = ?"
                            deleteGroupAuthoritiesSql = "delete from group_authorities where group_id = ?"
                            deleteGroupMembersSql = "delete from group_members where group_id = ?"
                            renameGroupSql = "update groups set group_name = ? where group_name = ?"
                            insertGroupMemberSql = "insert into group_members (group_id, username) values (?,?)"
                            deleteGroupMemberSql = "delete from group_members where group_id = ? and username = ?"
                            groupAuthoritiesSql = "select g.id, g.group_name, ga.authority from groups g, group_authorities ga where g.group_name = ? and g.id = ga.group_id "
                            deleteGroupAuthoritySql = "delete from group_authorities where group_id = ? and authority = ?"

                            authoritiesByUsernameQuery = "select username, authority from authorities where username = ?"
                            groupAuthoritiesByUsernameQuery = "select g.id, g.group_name, ga.authority from groups g, group_members gm, group_authorities ga where gm.username = ? and g.id = ga.group_id and g.id = gm.group_id"
                            usersByUsernameQuery = "select username, password, enabled from users where username = ?"
                        */
//                .usersByUsernameQuery(
//                        "select username, password, enabled from users where username = ?")
//                .authoritiesByUsernameQuery(
//                        "select username, authority from authorities where username = ?");

                //3.使用自定义的 UserDetailsService:
                /**
                 * http://www.mkyong.com/spring-security/spring-security-hibernate-annotation-example/
                 * http://blog.csdn.net/qq245671051/article/details/47259287
                 */
//                .userDetailsService(customUserDetailsService)
//                .passwordEncoder(passwordEncoder());
                //添加组权限:
                //1.使用 jdbc-user-service:
//                .jdbcAuthentication()
//                .authoritiesByUsernameQuery("")
//                .groupAuthoritiesByUsername("");
                //2.
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

    @Component
    public class CustomUserDetailsService implements UserDetailsService {

        @Transactional(readOnly = true)
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

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        /*If this is specified, “Persistent Token Approach” will be used. Defaults to “Simple Hash-Based Token Approach”*/
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}
