package com.wuspace.domain;

import com.wuspace.domain.enumeration.MediaType;
import com.wuspace.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * All manner of Works.
 */
@Getter
@Setter
@Entity
@MappedSuperclass
public class Works extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    /**
     * 媒体类型
     */
    @Column(name = "media_type")
    @Enumerated(value = EnumType.STRING)
    private MediaType mediaType;

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
    @JoinColumn(name = "works_content_id")
    private WorksContent worksContent;

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

    /**
     * 浏览次数
     */
    private Long viewedTimes = 0L;

    @Column(name = "status")
    private Integer status;


    /**
     * 获取去除 HTML 标签的 summary
     *
     * @return
     */
    public String getSummaryText() {
        return StringUtils.cleanHTML(getSummary());
    }

}
