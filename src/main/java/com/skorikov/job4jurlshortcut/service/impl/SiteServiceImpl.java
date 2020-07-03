package com.skorikov.job4jurlshortcut.service.impl;

import com.skorikov.job4jurlshortcut.model.Site;
import com.skorikov.job4jurlshortcut.model.Status;
import com.skorikov.job4jurlshortcut.repository.SiteRepository;
import com.skorikov.job4jurlshortcut.service.SiteService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Site service impl.
 */
@Service
public class SiteServiceImpl implements SiteService {
    /**
     * Site repository.
     */
    private final SiteRepository siteRepository;

    /**
     * Constructor.
     * @param siteRepository site repository.
     */
    @Autowired
    public SiteServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public Site registration(Site site) {
        Site registerSite = siteRepository.findBySite(site.getSite());
        if (registerSite == null) {
            registerSite = site;
            registerSite.setStatus(Status.ACTIVE);
            String password = RandomStringUtils.randomAlphanumeric(8);
            String encodedPassword = new BCryptPasswordEncoder().encode(password);
            registerSite.setPassword(encodedPassword);
            siteRepository.save(registerSite);
        } else {
            registerSite = new Site();
        }
        return registerSite;
    }

    @Override
    public List<Site> getAll() {
        return siteRepository.findAll();
    }

    @Override
    public Site findBySiteUrl(String url) {
        return siteRepository.findBySite(url);
    }

    @Override
    public Site findBySiteUrlAndPassword(String url, String password) {
        return siteRepository.findSiteBySiteAndPassword(url, password);
    }

    @Override
    public Site findById(Long id) {
        return siteRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        Site site = findById(id);
        site.setStatus(Status.DELETE);
        siteRepository.save(site);
    }
}
