package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "blogs")
public class Blog extends AbstractBaseEntity {

    private String category;

    private String title;

    private String content;

    private String tags;

    private Long viewedTimes = 0L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_type_id"))
    private Set<TopicType> topicTypes;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "zan_user_id"))
    private Set<User> zanUsers;

    private Long zanTimes = 0L;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "cai_user_id"))
    private Set<User> caiUsers;

    private Long caiTimes = 0L;

    @OneToMany(mappedBy = "blog")
    private Set<Comment> comments;

    private Long commentTimes = 0L;
}