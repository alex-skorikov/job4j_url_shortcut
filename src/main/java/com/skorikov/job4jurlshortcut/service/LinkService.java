package com.skorikov.job4jurlshortcut.service;

import com.skorikov.job4jurlshortcut.model.Link;

import java.util.List;

public interface LinkService {
    Link joinLinkToSite(String site, String link);

    List<Link> getAll();

    Link findByLink(String link);

    Link findByLinkCode(String code);

    Link findById(Long id);

    void delete(Long id);
}
