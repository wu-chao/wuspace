package com.wuspace.commons.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "authority", unique = true)
    private String authority;

    @NotNull
    @Column(name = "path", unique = true)
    private String path;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    public Authority() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority1 = (Authority) o;

        if (!authority.equals(authority1.authority)) return false;
        if (!path.equals(authority1.path)) return false;
        return name.equals(authority1.name);
    }

    @Override
    public int hashCode() {
        int result = authority.hashCode();
        result = 31 * result + path.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}