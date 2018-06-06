package com.github.wuchao.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "blogs")
public class Blog extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String category;

    private String title;

    @Lob
    private String content;

    private String tags;

    private Long viewedTimes = 0L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long zanTimes = 0L;

    private Long caiTimes = 0L;

    private Long commentTimes = 0L;

}