package com.wuspace.domain;

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
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "chn_name", unique = true)
    private String chnName;

    public Authority() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority = (Authority) o;

        if (!name.equals(authority.name)) return false;
        return chnName.equals(authority.chnName);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + chnName.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }
}