package com.github.wuchao.webproject.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id", "name"}, callSuper = true)
@Entity
@Table(name = "category")
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
     * 是否被禁用
     */
    @Column(name = "disabled")
    private boolean disabled;

    @Column(name = "category_id")
    private Long categoryId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    Set<Category> categories = new HashSet<>();

    public boolean isDisabled() {
        return disabled;
    }

}
