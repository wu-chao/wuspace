package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Comment extends BaseEntity {

    private User user;

    private User onUser;

    private Blog onBlog;

    private String content;

    private Set<Reply> replies;

    private Set<User> zanUsers;

    private Set<User> caiUsers;

}