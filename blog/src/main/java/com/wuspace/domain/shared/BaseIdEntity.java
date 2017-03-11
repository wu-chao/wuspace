package com.wuspace.domain.shared;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by WUCHAO on 2016/11/20.
 */
@Getter
@Setter
@MappedSuperclass
public class BaseIdEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
}
