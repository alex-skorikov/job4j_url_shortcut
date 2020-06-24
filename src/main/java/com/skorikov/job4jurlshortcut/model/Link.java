package com.skorikov.job4jurlshortcut.model;

import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

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

    public Link() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkCode() {
        return linkCode;
    }

    public void setLinkCode(String linkCode) {
        this.linkCode = linkCode;
    }

    public Integer getVizitCount() {
        return vizitCount;
    }

    public void setVizitCount(Integer vizitCount) {
        this.vizitCount = vizitCount;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
