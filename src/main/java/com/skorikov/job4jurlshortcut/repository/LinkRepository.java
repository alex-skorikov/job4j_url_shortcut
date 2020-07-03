package com.skorikov.job4jurlshortcut.repository;

import com.skorikov.job4jurlshortcut.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Link repository.
 */
@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    /**
     * Finde link from DB by link url.
     * @param link url.
     * @return link.
     */
    Link findByLink(String link);

    /**
     * Find link from DB by code.
     * @param code link code.
     * @return link.
     */
    Link findByLinkCode(String code);
}
