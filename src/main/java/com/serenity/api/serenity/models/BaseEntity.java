package com.serenity.api.serenity.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_modified", nullable = false)
    private LocalDateTime lastModified;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        lastModified = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        lastModified = LocalDateTime.now();
    }

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }

    public boolean isSoftDeleted() {
        return deletedAt != null;
    }
}

