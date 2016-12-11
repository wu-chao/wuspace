package com.wuspace.domain;

import com.wuspace.domain.shared.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by WUCHAO on 2016/12/4.
 */
@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority extends BaseEntity {

    @Column(name = "authority")
    private String authority;

    @Column(name = "description")
    private String description;

}
