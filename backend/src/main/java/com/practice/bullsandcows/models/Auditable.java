package com.practice.bullsandcows.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class Auditable {
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdWhen;

    @UpdateTimestamp
    private Date updatedWhen;

    public Date getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(Date createdWhen) {
        this.createdWhen = createdWhen;
    }

    public Date getUpdatedWhen() {
        return updatedWhen;
    }

    public void setUpdatedWhen(Date updatedWhen) {
        this.updatedWhen = updatedWhen;
    }
}