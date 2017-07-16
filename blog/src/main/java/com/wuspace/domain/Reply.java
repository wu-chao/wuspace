package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "replies")
public class Reply extends BaseEntity {

    private User user;

    private String content;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private User toUser;

    private Reply toReply;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "reply_id"),
            inverseJoinColumns = @JoinColumn(name = "zan_user_id"))
    private Set<User> zanUsers;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "reply_id"),
            inverseJoinColumns = @JoinColumn(name = "cai_user_id"))
    private Set<User> caiUsers;

}