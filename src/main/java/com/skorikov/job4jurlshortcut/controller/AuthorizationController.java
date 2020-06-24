package com.skorikov.job4jurlshortcut.controller;

import com.skorikov.job4jurlshortcut.model.Site;
import com.skorikov.job4jurlshortcut.security.jwt.JwtTokenProvider;
import com.skorikov.job4jurlshortcut.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for authorization site.
 */
@RestController
@RequestMapping("/login")
public class AuthorizationController {

    /**
     * Jwt token provaider.
     */
    private final JwtTokenProvider jwtTokenProvider;
    /**
     * Siteservice.
     */
    private final SiteService siteService;

    @Autowired
    public AuthorizationController(AuthenticationManager authenticationManager,
                                   JwtTokenProvider jwtTokenProvider,
                                   SiteService siteService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.siteService = siteService;
    }

    @PostMapping
    public ResponseEntity login(@RequestHeader String login,
                                @RequestHeader String password) {
        try {
            Site site = siteService.findBySiteUrlAndPassword(login, password);

            if (site == null) {
                throw new UsernameNotFoundException("User with username: " + site + " not found");
            }

            String token = jwtTokenProvider.createToken(login);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", login);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
