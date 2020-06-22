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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/login")
public class MainController {

    /**
     * Jwt token provaider.
     */
    private final JwtTokenProvider jwtTokenProvider;
    /**
     * Siteservice.
     */
    private final SiteService siteService;

    @Autowired
    public MainController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          SiteService siteService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.siteService = siteService;
    }

    /**
     * List for test.
     */
    private List<Map<String, String>> urls = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("url", "url_1");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("url", "url_2");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("url", "url_3");
        }});
    }};

//    @GetMapping
//    public List<Map<String, String>> list() {
//        return urls;
//    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Site> sites = siteService.getAll();

        model.put("sites", sites);

        return model.toString();
    }

    @PostMapping
    public ResponseEntity<Map<Object, Object>> login(@RequestHeader String login,
                                                     @RequestHeader String password) {
        System.out.println("Login = " + login);
        System.out.println("Password = " + password);
        try {
            Site user = siteService.findBySiteUrlAndPassword(login, password);
            System.out.println(user);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + login + " not found");
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

//    @PostMapping
//    public ResponseEntity<Map<Object, Object>> login(@RequestHeader String login,
//                                                     @RequestHeader String password) {
//        System.out.println("Login = " + login);
//        System.out.println("Password = " + password);
//        try {
//            Site user = siteService.findBySiteUrlAndPassword(login, password);
//            System.out.println(user);
//
//            if (user == null) {
//                throw new UsernameNotFoundException("User with username: " + login + " not found");
//            }
//
//            String token = jwtTokenProvider.createToken(login);
//
//            Map<Object, Object> response = new HashMap<>();
//            response.put("username", login);
//            response.put("token", token);
//
//            return ResponseEntity.ok(response);
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid username or password");
//        }
//    }
}
