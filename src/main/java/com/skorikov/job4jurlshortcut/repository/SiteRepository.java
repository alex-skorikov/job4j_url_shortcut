package com.skorikov.job4jurlshortcut.repository;

import com.skorikov.job4jurlshortcut.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    Site findBySite(String site);
    Site findBySiteAndPassword(String site, String password);
    Site findSiteBySiteAndPassword(String site, String password);
}
