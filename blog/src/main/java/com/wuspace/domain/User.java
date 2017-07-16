package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Setter
@Getter
@Entity(name = "users")
public class User extends BaseEntity {

    private String username;

    private String email;

    private String phone;

    private String password;

    private String nickname;

    private String avatar;

    private String description;

    private boolean enabled;

    @OneToMany(mappedBy = "user")
    private Set<Blog> blogs;

    @ManyToMany(mappedBy = "user")
    private Set<Blog> favorites;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

//    @OneToMany(mappedBy = "user")
//    private Set<User> following;
//
//    @OneToMany(mappedBy = "user")
//    private Set<User> followers;

    public User() {
    }

}