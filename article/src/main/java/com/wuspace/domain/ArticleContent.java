package com.wuspace.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"id"}, callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "article_contents")
public class ArticleContent extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    /**
     * 内容
     */
    @Lob
    private String content;

    @OneToOne
    @JoinColumn(name = "article_id")
    private Article article;

}
