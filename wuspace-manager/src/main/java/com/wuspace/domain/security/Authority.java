package com.wuspace.domain.security;

import com.wuspace.domain.shared.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by WUCHAO on 2017/1/9.
 */
@Getter
@Setter
@Entity
@Table(name = "authorities", uniqueConstraints = @UniqueConstraint(columnNames = {"authority", "username"}))
public class Authority extends BaseEntity {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

    public Authority() {}

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}