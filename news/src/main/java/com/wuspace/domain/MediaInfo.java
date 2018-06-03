package com.wuspace.domain;

import com.wuspace.domain.enumeration.CheckStatus;
import com.wuspace.domain.enumeration.MediaType;
import com.wuspace.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "publish_date")
    private ZonedDateTime publishDate;

    /**
     * 作者
     */
    @Column(name = "author_id")
    private Long authorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private User author;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

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
     * 来源
     */
    @Column(name = "source")
    private String source;

    /**
     * 来源 url
     */
    @Column(name = "source_url")
    private String sourceUrl;

    /**
     * 审核状态
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CheckStatus status;

    /**
     * 1表示被禁用的，0表示可用的
     */
    @Column(name = "is_disabled")
    private Integer isDisabled = 0;

    /**
     * 浏览次数
     */
    private Long viewedTimes = 0L;

    /**
     * 栏目
     */
    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    /**
     * 标签
     */
    @ManyToMany
    @JoinTable(name = "article_tags",
            joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>();

    @Column(name = "location")
    private String location;


    /**
     * 获取去除 HTML 标签的 summary
     *
     * @return
     */
    public String getSummaryText() {
        return StringUtils.cleanHTML(getSummary());
    }

}
