package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Set;

@Getter
@Setter
@Entity
public class Comment extends BaseEntity {

    private User user;

    private String content;

    private Blog blog;

    private Set<User> zanUsers;

    private Set<User> caiUsers;

    private Set<Reply> replies;

}