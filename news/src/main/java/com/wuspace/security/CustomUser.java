package com.wuspace.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUser extends User {

    private static final long serialVersionUID = 1L;

    private long userId;
    private String firstName;

    public CustomUser(long userId, String firstName, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
        this.firstName = firstName;
    }

}
