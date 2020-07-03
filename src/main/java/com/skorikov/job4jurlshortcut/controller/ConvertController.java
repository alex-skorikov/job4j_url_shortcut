package com.skorikov.job4jurlshortcut.controller;

import com.skorikov.job4jurlshortcut.model.Link;
import com.skorikov.job4jurlshortcut.service.LinkService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller for link register.
 */
@RestController
@RequestMapping("/convert")
public class ConvertController {
    /**
     * Link service.
     */
    private final LinkService linkService;

    /**
     * Constructor.
     *
     * @param linkService link servise.
     */
    @Autowired
    public ConvertController(LinkService linkService) {
        this.linkService = linkService;
    }

    /**
     * Method Post("/convert").
     *
     * @param site           site url.
     * @param authentication auth.
     * @return code.
     * Метод получает url ссылки, привязывает ее к сайту и возвращает
     * уникальный код.
     */
    @PostMapping
    public ResponseEntity<String> convert(@RequestBody Map<String, String> site, Authentication authentication) {
        String url = site.get("url");
        String siteName = authentication.getName();
        Link link = linkService.joinLinkToSite(siteName, url);

        JSONObject entity = new JSONObject();
        try {
            entity.put("code", link.getLinkCode());
        } catch (JSONException e) {
            //TODO add loger.
            e.printStackTrace();
        }
        return new ResponseEntity<>(entity.toString(), HttpStatus.OK);
    }
}
