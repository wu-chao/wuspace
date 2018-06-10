package com.github.wuchao.webproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @CreatedBy
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_date", nullable = false)
//    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
    @CreatedDate
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @JsonIgnore
    @Column(name = "last_modified_by", length = 50)
    @LastModifiedBy
    private String lastModifiedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_modified_date")
//    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
    @LastModifiedDate
    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

    /**
     * 1 表示已删除，0 表示未删除
     */
    @Column(name = "deleted")
    private Integer deleted = 0;

    public boolean isDeleted() {
        return deleted != null && deleted == 1;
    }

}