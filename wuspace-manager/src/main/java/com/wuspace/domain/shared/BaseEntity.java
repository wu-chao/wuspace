package com.wuspace.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by WUCHAO on 2016/11/20.
 */
@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class BaseEntity extends BaseIdEntity {

    @Column(name = "created_at")
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @JsonIgnore
    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = this.updatedAt = new Timestamp(new Date().getTime());
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Timestamp(new Date().getTime());
    }
}