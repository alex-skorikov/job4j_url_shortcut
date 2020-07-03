package com.skorikov.job4jurlshortcut.model;

import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Link entity.
 */
@Entity
@Table(name = "links")
public class Link extends BaseEntity {
    /**
     * Site link.
     */
    @Column(name = "link")
    private String link;

    /**
     * Link unique code.
     */
    @Column(name = "code")
    private String linkCode;

    /**
     * Link vizit count.
     */
    @Column(name = "vizit_count")
    private Integer vizitCount;
    /**
     * Link site.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    /**
     * Default constructor.
     */
    public Link() {
    }

    /**
     * Get link.
     * @return link.
     */
    public String getLink() {
        return link;
    }

    /**
     * Set lint.
     * @param link link.
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Get link code.
     * @return linkcode.
     */
    public String getLinkCode() {
        return linkCode;
    }

    /**
     * Set link code.
     * @param linkCode link code.
     */
    public void setLinkCode(String linkCode) {
        this.linkCode = linkCode;
    }

    /**
     * Get vizit count.
     * @return count.
     */
    public Integer getVizitCount() {
        return vizitCount;
    }

    /**
     * Set vizitcount.
     * @param vizitCount vizit count.
     */
    public void setVizitCount(Integer vizitCount) {
        this.vizitCount = vizitCount;
    }

    /**
     * Get site.
     * @return site.
     */
    public Site getSite() {
        return site;
    }

    /**
     * Set site fir link.
     * @param site site.
     */
    public void setSite(Site site) {
        this.site = site;
    }
}
