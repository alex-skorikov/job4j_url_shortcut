package com.skorikov.job4jurlshortcut.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

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
     * Site links.
     */
    @OneToMany(mappedBy = "site", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Link> links;

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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Site{"
                + "site='" + site + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
