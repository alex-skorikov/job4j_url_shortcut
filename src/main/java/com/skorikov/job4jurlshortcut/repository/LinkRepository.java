package com.skorikov.job4jurlshortcut.repository;

import com.skorikov.job4jurlshortcut.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    Link findByLink(String link);

    Link findByLinkCode(String code);
}
