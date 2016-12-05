package com.wuspace.domain;

import com.wuspace.domain.shared.BaseEntity;
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
public class Group extends BaseEntity {

    @Column(name = "group_name")
    private String groupName;

    @ManyToMany
    @JoinTable(name = "group_authorities"
            ,joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")}
            ,inverseJoinColumns = {@JoinColumn(name = "authority", referencedColumnName = "authority")})
    private List<Authority> authorities;

    @ManyToMany
    @JoinTable(name = "group_members"
            ,joinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")}
            ,inverseJoinColumns = {@JoinColumn(name = "username", referencedColumnName = "username")})
    private List<User> users;
}
