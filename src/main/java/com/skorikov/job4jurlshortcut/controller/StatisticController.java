package com.skorikov.job4jurlshortcut.controller;

import com.skorikov.job4jurlshortcut.model.Link;
import com.skorikov.job4jurlshortcut.model.Site;
import com.skorikov.job4jurlshortcut.service.LinkService;
import com.skorikov.job4jurlshortcut.service.SiteService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Statistics controller.
 */
@RestController
public class StatisticController {
    /**
     * Link service.
     */
    private final LinkService linkService;
    /**
     * Site service.
     */
    private final SiteService siteService;

    /**
     * Constructor.
     *
     * @param linkService link servise.
     * @param siteService siteservise.
     */
    @Autowired
    public StatisticController(LinkService linkService, SiteService siteService) {
        this.linkService = linkService;
        this.siteService = siteService;
    }

    /**
     * Get redirect link.
     *
     * @param code unique link code.
     * @return url.
     * <p>
     * Получает уникальный код ссылки и возвращает ее.
     */
    @GetMapping
    @RequestMapping("/redirect/")
    public ResponseEntity<String> redirect(@RequestParam String code) {
        String link = linkService.findByLinkCode(code).getLink();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("REDIRECT", link);
        return ResponseEntity.status(302)
                .headers(responseHeaders)
                .body("");
    }

    /**
     * Get statistics.
     *
     * @param authentication user(site) auth.
     * @return links list.
     * <p>
     * Отдает все ссылки которые привязаны к текущеу авторизованному пользователю(site).
     */
    @GetMapping
    @RequestMapping("/statistic")
    public ResponseEntity<String> statistic(Authentication authentication) {
        String siteUrl = authentication.getName();
        Site site = siteService.findBySiteUrl(siteUrl);
        List<Link> linkList = site.getLinks();

        JSONArray responseJson = new JSONArray();

        for (Link link : linkList) {
            JSONObject entity = new JSONObject();
            try {
                entity.put("url", link.getLink());
                entity.put("total", link.getVizitCount());
            } catch (JSONException e) {
                //TODO add loger.
                e.printStackTrace();
            }
            responseJson.put(entity);
        }
        return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
    }
}
