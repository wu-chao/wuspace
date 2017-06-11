package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Set;

@Entity
@Getter
@Setter
public class Blog extends BaseEntity {

    private User user;

    private String category;

    private Set<TopicType> topicTypes;

    private String title;

    private String content;

    private String tags;

    private Long viewedTimes;

    private Set<User> zanUsers;

    private Set<User> caiUsers;

}