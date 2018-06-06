package com.github.wuchao.webproject.security;

import com.github.wuchao.webproject.domain.User;
import com.github.wuchao.webproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Slf4j
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    private String roleAuthority;

    public DomainUserDetailsService() {
        super();
    }

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DomainUserDetailsService(UserRepository userRepository, String roleAuthority) {
        this.userRepository = userRepository;
        this.roleAuthority = roleAuthority;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(final String username) {
        log.debug("Authenticating {}", username);
        String lowercaseUsername = username.toLowerCase(Locale.ENGLISH);
        Optional<User> userOptional = userRepository.findOneWithAuthoritiesByUsername(lowercaseUsername);
        return userOptional.map(user -> {
            if (!user.isActivated()) {
                throw new UserNotActivatedException("User " + lowercaseUsername + " was not activated");
            }
            List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(grantedAuthorities) &&
                    grantedAuthorities.contains(new SimpleGrantedAuthority(this.roleAuthority))) {
                /*return new org.springframework.security.core.userdetails.User(lowercaseLogin,
                    user.getPassword(),
                    grantedAuthorities);*/
                return new CustomUser(user.getId(), user.getNickname(),
                        lowercaseUsername,
                        user.getPassword(),
                        grantedAuthorities);
            } else {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                RuntimeException exception = new RuntimeException("Access denied");
                saveException(request, exception);
                throw exception;
            }
        }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseUsername + " was not found in the " +
                "database"));
    }

    private void saveException(HttpServletRequest request, RuntimeException exception) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        } else {
            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        }
    }
}
