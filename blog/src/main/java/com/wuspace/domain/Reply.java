package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Set;

@Getter
@Setter
@Entity
public class Reply extends BaseEntity {

    private User user;

    private String content;

    private Comment comment;

    private User toUser;

    private Reply toReply;

    private Set<User> zanUsers;

    private Set<User> caiUsers;

}