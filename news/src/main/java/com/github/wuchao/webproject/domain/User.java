package com.github.wuchao.webproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = {"id", "username", "activated", "deleted"}, callSuper = true)
@ToString(exclude = {"authorities"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @NotNull
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Column(name = "nickname", length = 50, unique = true)
    private String nickname;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "avatar_url", length = 225)
    private String avatarUrl;

    @Column(name = "description", length = 500)
    private String description;

    @NotNull
    @Column(name = "activated", nullable = false)
    private Integer activated;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();

    public User id(Long id) {
        this.id = id;
        return this;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public User activated(Integer activated) {
        this.activated = activated;
        return this;
    }

    public boolean isActivated() {
        return activated != null && activated == 1;
    }

}