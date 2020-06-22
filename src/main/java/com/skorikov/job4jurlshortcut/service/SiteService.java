package com.skorikov.job4jurlshortcut.service;

import com.skorikov.job4jurlshortcut.model.Site;

import java.util.List;

public interface SiteService {
    Site registration(Site site);

    List<Site> getAll();

    Site findBySiteUrl(String url);

    Site findBySiteUrlAndPassword(String url, String password);

    Site findById(Long id);

    void delete(Long id);
}
