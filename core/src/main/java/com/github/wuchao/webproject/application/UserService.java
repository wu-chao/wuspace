package com.github.wuchao.webproject.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing users.
 */
@Slf4j
@Service
@Transactional
public class UserService {

//    private final PasswordEncoder passwordEncoder;
//
//    private AuthenticationManager authenticationManager;
//
//    public UserService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//    }
//
//    public void login(String username, String password, Collection<Authority> authorities) {
//        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Authority authority : authorities) {
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return authority.getName();
//                }
//            });
//        }
//        Authentication auth = new UsernamePasswordAuthenticationToken(username.trim(), password.trim(), grantedAuthorities);
//        auth = authenticationManager.authenticate(auth);
//        SecurityContextHolder.getContext().setAuthentication(auth);
//    }
//
//    public static void updateAuthentication(String username, Collection<Authority> authorities) {
//        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Authority authority : authorities) {
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return authority.getName();
//                }
//            });
//        }
//        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(username.trim(), "", grantedAuthorities);
//        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
//        SecurityContextHolder.getContext().setAuthentication(auth);
//    }
}
