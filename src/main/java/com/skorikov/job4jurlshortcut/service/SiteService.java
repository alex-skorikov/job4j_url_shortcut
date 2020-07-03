package com.skorikov.job4jurlshortcut.service;

import com.skorikov.job4jurlshortcut.model.Site;

import java.util.List;

/**
 * Site service.
 */
public interface SiteService {
    /**
     * Registration site in DB.
     *
     * @param site site url.
     * @return site.
     */
    Site registration(Site site);

    /**
     * Get all sitesfrom DB.
     *
     * @return sites list.
     */
    List<Site> getAll();

    /**
     * Find site from DB by site url.
     *
     * @param url site url.
     * @return site.
     */
    Site findBySiteUrl(String url);

    /**
     * Find site by siteurl andsitepassword.
     *
     * @param url      site url.
     * @param password site password.
     * @return site.
     */
    Site findBySiteUrlAndPassword(String url, String password);

    /**
     * Findsite by id.
     *
     * @param id siteid.
     * @return site.
     */
    Site findById(Long id);

    /**
     * Change site status on DELETE.
     *
     * @param id site id.
     */
    void delete(Long id);
}
