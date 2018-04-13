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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getViewedTimes() {
        return viewedTimes;
    }

    public void setViewedTimes(Long viewedTimes) {
        this.viewedTimes = viewedTimes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getZanTimes() {
        return zanTimes;
    }

    public void setZanTimes(Long zanTimes) {
        this.zanTimes = zanTimes;
    }

    public Long getCaiTimes() {
        return caiTimes;
    }

    public void setCaiTimes(Long caiTimes) {
        this.caiTimes = caiTimes;
    }

    public Long getCommentTimes() {
        return commentTimes;
    }

    public void setCommentTimes(Long commentTimes) {
        this.commentTimes = commentTimes;
    }
}