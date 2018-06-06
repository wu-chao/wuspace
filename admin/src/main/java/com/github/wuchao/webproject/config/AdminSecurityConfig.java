package com.github.wuchao.webproject.config;

import com.github.wuchao.webproject.repository.UserRepository;
import com.github.wuchao.webproject.security.DomainUserDetailsService;
import com.github.wuchao.webproject.repository.UserRepository;
import com.github.wuchao.webproject.security.DomainUserDetailsService;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Order(value = 1)
@Component(value = "adminSecurityConfig")
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @EnableAutoConfiguration作用： Spring Boot 会自动根据你 jar 包的依赖来自动配置项目。例如当你项目下面有HSQLDB的依赖时，
     * Spring Boot 会创建默认的内存数据库的数据源DataSource，如果你自己创建了DataSource，
     * Spring Boot 就不会创建默认的DataSource。
     */
//    @Autowired
//    private DataSource dataSource;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserRepository userRepository;

    public AdminSecurityConfig(AuthenticationManagerBuilder authenticationManagerBuilder, UserRepository userRepository) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        try {
            authenticationManagerBuilder
                    .userDetailsService(new DomainUserDetailsService(this.userRepository, "ROLE_ADMIN"))
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/bower_components", "/bower_components/jquery/dist/**", "/bower_components/bootstrap/dist/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .formLogin()
                .loginPage("/admin/login")
//                .successHandler(customAuthenticationSuccessHandler)
                .loginProcessingUrl("/authentication")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .antMatchers("/admin/login/**").permitAll()
                .antMatchers("/admin/logout/**").permitAll();

        //添加自定义的Filter，用于登录校验验证码
//        http.addFilterBefore(new VerificationCodeAuthenticationFilter("/admin/authentication", new SimpleUrlAuthenticationFailureHandler("/admin?error")), UsernamePasswordAuthenticationFilter.class);

    }

    /*
    * There was an unexpected error (type=Forbidden, status=403).
Could not verify the provided CSRF token because your session was not found.



/**
 * https://stackoverflow.com/questions/35218354/difference-between-registerglobal-configure-configureglobal-configureglo
 *
 * @param auth
 * @throws Exception
 */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username, password, activated from users where username = ?")
//                .authoritiesByUsernameQuery(
//                        "select u.username, ua.authority from users as u, user_authority as ua where u.username = ? and u.id = ua.user_id")
//
//                .passwordEncoder(passwordEncoder())
//        ;
//    }

    /**
     * http://blog.csdn.net/w605283073/article/details/51322771
     */
//    @Autowired
//    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

//    @Component
//    public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//        private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//        private String targetUrl = "localhost:8009/admin/home";
//
//        @Override
//        protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//            String targetUrl = determineTargetUrl(request, response);
//            if (response.isCommitted()) {
//                this.logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
//            } else {
//                this.redirectStrategy.sendRedirect(request, response, targetUrl);
//            }
//        }
//
//        @Override
//        protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
//
//            return targetUrl;
//        }
//    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
