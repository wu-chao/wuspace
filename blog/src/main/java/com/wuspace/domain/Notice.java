package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
public class Notice extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "publish_by")
    private User publishBy;

    @ManyToOne
    @JoinColumn(name = "delete_by")
    private User deleteBy;

    private String title;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content", nullable = false)
    private String content;

    public Notice() {
    }

    public Notice(User admin, String title, String content, Timestamp timestamp) {

    }

}
