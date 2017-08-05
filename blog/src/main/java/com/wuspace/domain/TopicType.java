package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "topic_types")
public class TopicType extends AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String name;

    private String description;

//    @ManyToMany(mappedBy = "topicTypes")
//    private Set<Blog> blogs;

}
