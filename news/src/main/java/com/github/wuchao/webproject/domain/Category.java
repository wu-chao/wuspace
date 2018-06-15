package com.github.wuchao.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
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
     * 是否启用
     */
    @Column(name = "activated")
    private boolean activated;

    @Column(name = "category_id")
    private Long categoryId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    Set<Category> categories = new HashSet<>();

    public boolean isActivated() {
        return activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
