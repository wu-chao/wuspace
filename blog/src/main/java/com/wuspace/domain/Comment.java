package com.wuspace.domain;

import com.wuspace.commons.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

//    @ManyToOne
//    @JoinColumn(name = "blog_id")
//    private Blog blog;

//    @ManyToOne
//    @JoinColumn(name = "comment_id")
//    private Comment comment;
//
//    @OneToMany(mappedBy = "comment")
//    private Set<Comment> replies;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "zan_user_id"))
    private Set<User> zanUsers;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "cai_user_id"))
    private Set<User> caiUsers;
}