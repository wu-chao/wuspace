package com.wuspace.commons.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority_name", nullable = false)
    private String authorityName;

    @Column(name = "username")
    private String username;

    public Authority() {
    }

    public Authority(String username, String authorityName) {
        this.username = username;
        this.authorityName = authorityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority = (Authority) o;

        if (!id.equals(authority.id)) return false;
        if (!authorityName.equals(authority.authorityName)) return false;
        return username.equals(authority.username);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + authorityName.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}