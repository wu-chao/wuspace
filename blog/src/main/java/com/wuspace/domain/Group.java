package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "groups")
public class Group extends AbstractBaseEntity {

    private String name;

}
