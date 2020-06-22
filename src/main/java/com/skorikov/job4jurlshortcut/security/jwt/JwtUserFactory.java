package com.skorikov.job4jurlshortcut.security.jwt;

import com.skorikov.job4jurlshortcut.model.Site;

import java.util.ArrayList;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(Site site) {
        return new JwtUser(
                site.getSite(),
                site.getPassword(),
                new ArrayList<>()
        );
    }
}

