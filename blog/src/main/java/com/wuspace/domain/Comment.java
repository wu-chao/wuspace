package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "comments")
public class Comment extends BaseEntity {

    private User user;

    private String content;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "zan_user_id"))
    private Set<User> zanUsers;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "cai_user_id"))
    private Set<User> caiUsers;

    @OneToMany(mappedBy = "comment")
    private Set<Reply> replies;

}