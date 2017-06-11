package com.wuspace.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class BaseEntity extends BaseIdEntity {

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @JsonIgnore
    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = this.updatedAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }
}