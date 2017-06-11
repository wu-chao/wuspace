package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Set;

@Getter
@Setter
@Entity
public class Reply extends BaseEntity {

    private User user;

    private User toUser;

    private Comment toComment;

    private String content;

}