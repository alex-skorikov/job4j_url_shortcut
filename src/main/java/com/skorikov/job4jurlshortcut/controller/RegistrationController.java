package com.skorikov.job4jurlshortcut.controller;

import com.skorikov.job4jurlshortcut.model.Site;
import com.skorikov.job4jurlshortcut.service.SiteService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller for registration site.
 */
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    /**
     * Site service.
     */
    private final SiteService siteService;

    /**
     * Constructor.
     * @param siteService site servise.
     */
    @Autowired
    public RegistrationController(SiteService siteService) {
        this.siteService = siteService;
    }

    /**
     * Registration site.
     *
     * @param site url.
     * @return login, password registration is valid.
     * <p>
     * Метод получает url сайта, если такой отсутствуетв базе - регистрирует и
     * возвращает логин, пароль и bool true. Если сайт есть в ситстеме - возвращает false.
     */
    @PostMapping
    public ResponseEntity<String> registration(@RequestBody Map<String, String> site) {
        Site newSite = new Site();
        newSite.setSite(site.get("site"));
        newSite = siteService.registration(newSite);
        JSONObject entity = new JSONObject();
        boolean isRegitration = false;
        if (newSite.getSite() != null) {
            isRegitration = true;
        }
        try {
            entity.put("registration", isRegitration);
            entity.put("login", newSite.getSite());
            entity.put("password", newSite.getPassword());
        } catch (JSONException e) {
            //TODO add logger
            e.printStackTrace();
        }

        return new ResponseEntity<>(entity.toString(), HttpStatus.OK);
    }
}
