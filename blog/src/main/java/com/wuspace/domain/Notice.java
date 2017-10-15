package com.wuspace.domain;

import com.wuspace.commons.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
public class Notice extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

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
