package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String username;

    private String email;

    private String phone;

    private String password;

    private String nickname;

    private String avatar;

    private String description;

    private boolean enabled;

//    @OneToMany(mappedBy = "user")
//    private Set<Blog> blogs;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "user")
    private Set<Blog> favorites;

    @OneToMany(mappedBy = "user")
    private Set<User> following;

    @OneToMany(mappedBy = "user")
    private Set<User> followers;

    public User() {
    }
}