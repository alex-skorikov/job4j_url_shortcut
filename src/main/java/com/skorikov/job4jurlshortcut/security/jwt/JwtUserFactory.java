package com.skorikov.job4jurlshortcut.security.jwt;

import com.skorikov.job4jurlshortcut.model.Site;

import java.util.ArrayList;

/**
 * Jwt user factory for create jwt user..
 */
public final class JwtUserFactory {
    /**
     * Default constructor.
     */
    public JwtUserFactory() {
    }

    /**
     * Create jwtUser.
     * @param site site.
     * @return jwtUser.
     */
    public static JwtUser create(Site site) {
        return new JwtUser(
                site.getSite(),
                site.getPassword(),
                new ArrayList<>()
        );
    }
}

