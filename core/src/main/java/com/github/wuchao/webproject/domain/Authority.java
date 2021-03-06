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
@Table(name = "authority")
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "value", unique = true)
    private String value;

    public Authority name(String name) {
        this.name = name;
        return this;
    }

    public Authority value(String value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(name, authority.name) &&
                Objects.equals(value, authority.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, value);
    }
}