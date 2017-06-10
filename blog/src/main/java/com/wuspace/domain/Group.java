package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Group extends BaseEntity {

    private String name;

    private List<Authority> authorities;

    private List<User> users;
}
