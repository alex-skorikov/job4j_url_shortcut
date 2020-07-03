package com.skorikov.job4jurlshortcut.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.util.Date;

/**
 * Base entity.
 * Базовая сущность.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    /**
     * Entity ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Entity create date.
     */
    @CreatedDate
    @Column(name = "created")
    private Date created;

    /**
     * Entity last update.
     */
    @LastModifiedDate
    @Column(name = "update")

    private Date update;
    /**
     * Entity status.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    /**
     * Default constructor.
     */
    public BaseEntity() {
    }

    /**
     * Get id.
     *
     * @return entity id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get date created.
     *
     * @return date.
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Set date created.
     *
     * @param created date.
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Get date update.
     *
     * @return date.
     */
    public Date getUpdate() {
        return update;
    }

    /**
     * Set date update.
     *
     * @param update date.
     */
    public void setUpdate(Date update) {
        this.update = update;
    }

    /**
     * Get status.
     *
     * @return entity status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set status.
     *
     * @param status status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
