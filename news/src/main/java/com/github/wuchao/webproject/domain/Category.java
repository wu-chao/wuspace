package com.github.wuchao.webproject.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id", "name"}, callSuper = true)
@Entity
@Table(name = "categories")
public class Category extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    /**
     * 英文名称
     */
    @Column(name = "name", unique = true)
    private String name;

    /**
     * 中文名称
     */
    @Column(name = "value")
    private String value;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 1 表示被禁用的，0 表示可用的
     */
    @Column(name = "disabled")
    private Integer disabled = 0;

    @Column(name = "category_id")
    private Long categoryId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    Set<Category> categories = new HashSet<>();

    public boolean isDisabled() {
        return disabled != null && disabled == 1;
    }

}
