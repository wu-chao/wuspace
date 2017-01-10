package com.wuspace.domain.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by WUCHAO on 2017/1/9.
 */
@Getter
@Setter
@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"role", "username"}))
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    public UserRole() {}

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }
}
