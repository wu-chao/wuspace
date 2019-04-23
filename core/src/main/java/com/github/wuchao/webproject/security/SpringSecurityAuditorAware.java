package com.github.wuchao.webproject.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        CustomUser user = SecurityUtils.getCurrentUser();
        return Optional.ofNullable(user != null ? String.valueOf(user.getUserId()) : null);
    }
}
