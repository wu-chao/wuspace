package com.github.wuchao.webproject.domain;

import com.github.wuchao.webproject.domain.enumeration.CheckStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "media_comment")
public class MediaComment extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    /**
     * 评论人
     */
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    /**
     * 评论内容
     */
    @Lob
    @Column(name = "content")
    private String content;

    /**
     * 评论审核状态
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CheckStatus status;

    /**
     * 评论所属内容
     */
    @Column(name = "media_info_id")
    private Long mediaInfoId;

    /**
     * 父级评论
     */
    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id", insertable = false, updatable = false)
    private MediaComment parentComment;

//
//    @OneToMany(mappedBy = "comment")
//    private Set<Comment> replies;

//    @ManyToMany
//    @JoinTable(joinColumns = @JoinColumn(name = "comment_id"),
//            inverseJoinColumns = @JoinColumn(name = "zan_user_id"))
//    private Set<User> zanUsers;
//
//    @ManyToMany
//    @JoinTable(joinColumns = @JoinColumn(name = "comment_id"),
//            inverseJoinColumns = @JoinColumn(name = "cai_user_id"))
//    private Set<User> caiUsers;

}