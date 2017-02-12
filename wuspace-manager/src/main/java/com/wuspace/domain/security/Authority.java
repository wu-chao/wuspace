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
//@Table(name = "authorities", uniqueConstraints = @UniqueConstraint(columnNames = {"authority", "username"}))
@Table(name = "authorities")
public class Authority extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

    public Authority() {}

    public Authority(User user, String authority) {
        this.user = user;
        this.authority = authority;
    }
}