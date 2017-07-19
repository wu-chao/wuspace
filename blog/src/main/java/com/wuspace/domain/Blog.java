package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "blogs")
public class Blog extends BaseEntity {

    private String category;


    private String title;

    private String content;

    private String tags;

    private Long viewedTimes;

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

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "cai_user_id"))
    private Set<User> caiUsers;

    @OneToMany(mappedBy = "blog")
    private Set<Comment> comments;

}