package com.skorikov.job4jurlshortcut.security;


import com.skorikov.job4jurlshortcut.model.Site;
import com.skorikov.job4jurlshortcut.security.jwt.JwtUser;
import com.skorikov.job4jurlshortcut.security.jwt.JwtUserFactory;
import com.skorikov.job4jurlshortcut.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Jwt user detail service for get userdetails.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    /**
     * Site service.
     */
    private final SiteService siteService;

    /**
     * Constructor.
     * @param siteService site service.
     */
    @Autowired
    public JwtUserDetailsService(SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public UserDetails loadUserByUsername(String siteUrl) throws UsernameNotFoundException {
        Site site = siteService.findBySiteUrl(siteUrl);

        if (site == null) {
            //TODO add logger.
            throw new UsernameNotFoundException("User with username: " + siteUrl + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(site);
        return jwtUser;
    }
}