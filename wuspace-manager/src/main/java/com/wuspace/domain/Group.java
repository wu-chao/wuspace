package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by WUCHAO on 2016/12/4.
 */
@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name")
    private String name;

    @ManyToMany
    @JoinTable(name = "group_authorities"
            ,joinColumns = {@JoinColumn(name = "group_id")}
            ,inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private List<authority> authorities;

    @ManyToMany
    @JoinTable(name = "group_members"
            ,joinColumns = {@JoinColumn(name = "group_id")}
            ,inverseJoinColumns = {@JoinColumn(name = "username")})
    private List<User> users;
}
