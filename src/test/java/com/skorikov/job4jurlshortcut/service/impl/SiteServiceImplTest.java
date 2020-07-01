package com.skorikov.job4jurlshortcut.service.impl;

import com.skorikov.job4jurlshortcut.model.Site;
import com.skorikov.job4jurlshortcut.model.Status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest

@TestPropertySource("/application.test.properties")
@Sql(value = {"/create_site_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_site_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class SiteServiceImplTest {

    @Autowired
    private SiteServiceImpl siteService;

    @Test
    void whenRegistrationNewSiteThenReturnSite() {
        Site site = new Site();
        site.setSite("new test site");
        Site returnSite = this.siteService.registration(site);
        assertNotNull(returnSite);
        assertTrue(returnSite.getSite().equals("new test site"));
        assertNotNull(returnSite.getPassword());
        assertNotNull(returnSite.getCreated());
        assertNotNull(returnSite.getUpdate());
        assertEquals(returnSite.getStatus(), Status.ACTIVE);
    }

    @Test
    void whenGetAllSitesThenReturnList() {
        List<Site> list = this.siteService.getAll();
        assertNotEquals(list.size(), is(2));
    }

    @Test
    void whenFindBySiteUrlthenReturnSite() {
        Site site = this.siteService.findBySiteUrl("site_1");
        assertNotNull(site);
        assertEquals(site.getPassword(),
                "$2a$08$fNUHI3FnO3cbT6VAcClJOOsIq93f2101ud2RAKiZFAh7Y2h.oFRzC");
        assertEquals(site.getStatus(), Status.ACTIVE);
    }

    @Test
    void whenFindBySiteUrlAndPasswordthenReturnSite() {
        Site site = this.siteService
                .findBySiteUrlAndPassword("site_2",
                        "$2a$08$fNUHI3FnO3cbT6VAcClJOOsIq93f2101ud2RAKiZFAh7Y2h.oFRzC");
        assertNotNull(site);
    }

    @Test
    void whenFindByIdThenReturnSite() {
        Site site = this.siteService.findById(11L);
        assertNotNull(site);
        assertEquals(site.getSite(), "site_1");
    }

    @Test
    void whenDeleteSiteThenChangeSiteStaus() {
        Site site = this.siteService.findById(11L);
        assertNotNull(site);
        assertEquals(site.getStatus(), Status.ACTIVE);

        this.siteService.delete(11L);

        Site testSite = this.siteService.findById(11L);
        assertEquals(testSite.getStatus(), Status.DELETE);
    }
}