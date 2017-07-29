package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    public Set<TopicType> getTopicTypes() {
        return topicTypes;
    }

    public void setTopicTypes(Set<TopicType> topicTypes) {
        this.topicTypes = topicTypes;
    }

    public Set<User> getZanUsers() {
        return zanUsers;
    }

    public void setZanUsers(Set<User> zanUsers) {
        this.zanUsers = zanUsers;
    }

    public Long getZanTimes() {
        return zanTimes;
    }

    public void setZanTimes(Long zanTimes) {
        this.zanTimes = zanTimes;
    }

    public Set<User> getCaiUsers() {
        return caiUsers;
    }

    public void setCaiUsers(Set<User> caiUsers) {
        this.caiUsers = caiUsers;
    }

    public Long getCaiTimes() {
        return caiTimes;
    }

    public void setCaiTimes(Long caiTimes) {
        this.caiTimes = caiTimes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Long getCommentTimes() {
        return commentTimes;
    }

    public void setCommentTimes(Long commentTimes) {
        this.commentTimes = commentTimes;
    }
}