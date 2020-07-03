package com.skorikov.job4jurlshortcut.service;

import com.skorikov.job4jurlshortcut.model.Link;

import java.util.List;

/**
 * Link service.
 */
public interface LinkService {
    /**
     * Join linl and site.
     * @param site site url.
     * @param link link url.
     * @return link.
     */
    Link joinLinkToSite(String site, String link);

    /**
     * Get all links.
     * @return links list.
     */
    List<Link> getAll();

    /**
     * Find link by link url.
     * @param link link url.
     * @return link.
     */
    Link findByLink(String link);

    /**
     * Find link by link code.
     * @param code link code.
     * @return link.
     */
    Link findByLinkCode(String code);

    /**
     * Find link by id.
     * @param id link id.
     * @return link.
     */
    Link findById(Long id);

    /**
     * Change link statuson on DELETE.
     * @param id link id.
     */
    void delete(Long id);
}
