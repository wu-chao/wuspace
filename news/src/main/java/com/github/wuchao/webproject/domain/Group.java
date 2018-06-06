package com.github.wuchao.webproject.domain;

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
@Table(name = "groups")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "chn_name", unique = true)
    private String chnName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (!name.equals(group.name)) return false;
        return chnName.equals(group.chnName);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + chnName.hashCode();
        return result;
    }
}
