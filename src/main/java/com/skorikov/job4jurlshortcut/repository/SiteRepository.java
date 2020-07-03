package com.skorikov.job4jurlshortcut.repository;

import com.skorikov.job4jurlshortcut.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Site repository.
 */
@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    /**
     * Find site from DB by site url.
     *
     * @param site url.
     * @return site.
     */
    Site findBySite(String site);

    /**
     * Find site fron DB by site url and password.
     *
     * @param site     url.
     * @param password password.
     * @return site.
     */
    Site findSiteBySiteAndPassword(String site, String password);
}
