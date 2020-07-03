package com.skorikov.job4jurlshortcut.service.impl;

import com.skorikov.job4jurlshortcut.model.Link;
import com.skorikov.job4jurlshortcut.model.Site;
import com.skorikov.job4jurlshortcut.model.Status;
import com.skorikov.job4jurlshortcut.repository.LinkRepository;
import com.skorikov.job4jurlshortcut.repository.SiteRepository;
import com.skorikov.job4jurlshortcut.service.LinkService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Link service impl.
 */
@Service
public class LinkServiceImpl implements LinkService {
    /**
     * Link site repository.
     */
    private final LinkRepository linkRepository;
    /**
     * Site repository.
     */
    private final SiteRepository siteRepository;

    /**
     * Constructor.
     * @param linkRepository link repository.
     * @param siteRepository site repository.
     */
    @Autowired
    public LinkServiceImpl(LinkRepository linkRepository, SiteRepository siteRepository) {
        this.linkRepository = linkRepository;
        this.siteRepository = siteRepository;
    }

    @Override
    public Link joinLinkToSite(String siteUrl, String link) {
        Link newLink = linkRepository.findByLink(link);
        Site site = siteRepository.findBySite(siteUrl);
        if (newLink == null) {
            String code = RandomStringUtils.randomAlphanumeric(6);
            newLink = new Link();
            newLink.setLink(link);
            newLink.setLinkCode(code);
            newLink.setSite(site);
            newLink.setStatus(Status.ACTIVE);
            newLink.setVizitCount(1);
        } else {
            Integer vizitCount = newLink.getVizitCount();
            vizitCount++;
            newLink.setVizitCount(vizitCount);
        }
        linkRepository.save(newLink);
        return newLink;
    }

    @Override
    public List<Link> getAll() {
        return linkRepository.findAll();
    }

    @Override
    public Link findByLink(String link) {
        return linkRepository.findByLink(link);
    }

    @Override
    public Link findByLinkCode(String code) {
        return linkRepository.findByLinkCode(code);
    }

    @Override
    public Link findById(Long id) {
        return linkRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        Link link = findById(id);
        link.setStatus(Status.DELETE);
        linkRepository.save(link);
    }
}
