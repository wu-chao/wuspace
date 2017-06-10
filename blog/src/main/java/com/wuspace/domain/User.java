package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Set;

@Setter
@Getter
@Entity
public class User extends BaseEntity {

    private String username;

    private String email;

    private String phone;

    private String password;

    private String nickname;

    private String avatar;

    private String description;

    private boolean enabled;

    private Set<Blog> blogs;

    private Set<Comment> comments;

    private Set<Blog> favorites;

    private Set<User> following;

    private Set<User> followers;

    public User() {
    }

}