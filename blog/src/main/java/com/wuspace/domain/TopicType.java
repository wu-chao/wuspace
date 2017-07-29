package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@Entity(name = "topic_types")
public class TopicType extends AbstractBaseEntity {

    private String name;

    private String description;

    @ManyToMany(mappedBy = "topicTypes")
    private Set<Blog> blogs;

}
