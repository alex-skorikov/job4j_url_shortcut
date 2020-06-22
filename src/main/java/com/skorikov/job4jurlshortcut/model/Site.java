package com.skorikov.job4jurlshortcut.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sites")
public class Site extends BaseEntity {
    /**
     * Site url.
     */
    @Column(name = "site_url")
    private String site;
    /**
     * Site password.
     */
    @Column(name = "password")
    private String password;

    /**
     * Default constructor.
     */
    public Site() {
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Site{"
                + "site='" + site + '\''
                + ", password='" + password + '\''
                + '}';
    }
}