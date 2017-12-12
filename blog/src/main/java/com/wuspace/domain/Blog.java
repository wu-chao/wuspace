package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "blogs")
public class Blog extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String category;

    private String title;

    @Lob
    private String content;

    private String tags;

    private Long viewedTimes = 0L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToMany
//    @JoinTable(joinColumns = @JoinColumn(name = "blog_id"),
//            inverseJoinColumns = @JoinColumn(name = "topic_type_id"))
//    private Set<TopicType> topicTypes;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "blogs_zan_users",
//            joinColumns = @JoinColumn(name = "blog_id"),
//            inverseJoinColumns = @JoinColumn(name = "zan_user_id"))
//    private Set<User> zanUsers;

    private Long zanTimes = 0L;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "blogs_cai_users",
//            joinColumns = @JoinColumn(name = "blog_id"),
//            inverseJoinColumns = @JoinColumn(name = "cai_user_id"))
//    private Set<User> caiUsers;

    private Long caiTimes = 0L;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "blog")
//    private Set<Comment> comments;

    private Long commentTimes = 0L;

    public Blog() {}

    public Blog(Long id) {
        this.id = id;
    }

}