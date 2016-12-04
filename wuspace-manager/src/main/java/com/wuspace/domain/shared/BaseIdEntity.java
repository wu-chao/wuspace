package com.wuspace.domain.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;

/**
 * Created by WUCHAO on 2016/11/20.
 */
@EqualsAndHashCode
@Getter
@Setter
@MappedSuperclass
public class BaseIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
    @Column(name = "id")
    private Long id;
}
