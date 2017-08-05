package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority extends AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String username;

    private String authority;

    public Authority() {
    }

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}