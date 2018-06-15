package com.github.wuchao.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "value", unique = true)
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(value, group.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, value);
    }
}
