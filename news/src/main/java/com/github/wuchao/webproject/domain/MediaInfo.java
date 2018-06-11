package com.github.wuchao.webproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.wuchao.webproject.domain.enumeration.CheckStatus;
import com.github.wuchao.webproject.domain.enumeration.MediaType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "media_info")
public class MediaInfo extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    /**
     * 栏目
     */
    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    /**
     * 作者
     */
    @Column(name = "author_id")
    private Long authorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private User author;

    /**
     * 发布时间
     */
    @Column(name = "published_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime publishedDate;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 缩略图个数
     */
    @Column(name = "thumbnail_num")
    private int thumbnailNum = 0;

    /**
     * 缩略图 URL
     */
    @Column(name = "thumbnail_urls")
    private String thumbnailUrls;

    /**
     * 内容
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_content_id")
    private MediaContent mediaContent;

    /**
     * 媒体类型
     */
    @Column(name = "media_type")
    @Enumerated(value = EnumType.STRING)
    private MediaType mediaType;

    /**
     * Tag
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "media_info_tag",
            joinColumns = @JoinColumn(name = "media_info_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    /**
     * 来源主体 ID
     */
    @Column(name = "source_id")
    private Long sourceId;

    /**
     * 原文来源账号主体
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", insertable = false, updatable = false)
    private MediaSource mediaSource;

    /**
     * 审核状态
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CheckStatus status;

    /**
     * 1表示被禁用的，0表示可用的
     */
//    @Column(name = "disabled")
//    private Integer disabled = 0;

    /**
     * 浏览次数
     */
    private Long viewedTimes = 0L;


    @Column(name = "location")
    private String location;


}
