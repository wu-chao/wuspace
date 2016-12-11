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
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@MappedSuperclass
public class BaseEntity extends BaseIdEntity implements java.io.Serializable {

    @Column(name = "created_at")
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @JsonIgnore
    @Column(name = "deleted_at")
    private Timestamp deletedAt;

/*
    @Column(name = "is_deleted")
    private Integer isDeleted = 0;
*/

    @PrePersist
    public void prePersist() {
        this.createdAt = this.updatedAt = new Timestamp(new Date().getTime());
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Timestamp(new Date().getTime());
    }
}
