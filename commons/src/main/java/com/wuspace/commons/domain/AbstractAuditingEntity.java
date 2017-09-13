package com.wuspace.commons.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @CreatedBy
    private String createdBy;

    @JsonIgnore
    @Column(name = "created_date", nullable = false)
//    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
    @CreatedDate
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @JsonIgnore
    @Column(name = "last_modified_by", length = 50)
    @LastModifiedBy
    private String lastModifiedBy;

    @JsonIgnore
    @Column(name = "last_modified_date")
//    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
    @LastModifiedDate
    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}