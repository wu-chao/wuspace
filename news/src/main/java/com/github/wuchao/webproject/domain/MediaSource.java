package com.github.wuchao.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "media_source")
public class MediaSource extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;


    /**
     * logo URL
     */
    @Column(name = "logo_url")
    private String logoUrl;


}
