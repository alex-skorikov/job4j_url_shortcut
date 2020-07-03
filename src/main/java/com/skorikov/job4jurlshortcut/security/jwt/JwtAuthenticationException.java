package com.skorikov.job4jurlshortcut.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Jwt auth exception.
 */
public class JwtAuthenticationException extends AuthenticationException {

    /**
     * Exception.
     * @param msg message.
     */
    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
