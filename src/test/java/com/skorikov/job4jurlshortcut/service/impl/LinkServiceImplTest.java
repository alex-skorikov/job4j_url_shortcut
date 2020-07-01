package com.skorikov.job4jurlshortcut.service.impl;

import com.skorikov.job4jurlshortcut.model.Link;
import com.skorikov.job4jurlshortcut.model.Status;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest

@TestPropertySource("/application.test.properties")
@Sql(value = {"/create_site_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_site_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class LinkServiceImplTest {

    @Autowired
    private LinkServiceImpl linkService;

    @Test
    void whenJoinLinkToSiteThenReturnLink() {
        Link link = this.linkService.joinLinkToSite("site_1", "new link to site");

        Assert.assertNotNull(link);
        Assert.assertThat(link.getLink(), is("new link to site"));
        Assert.assertThat(link.getVizitCount(), is(1));

        link = this.linkService.joinLinkToSite("site_1", "new link to site");

        Assert.assertThat(link.getVizitCount(), is(2));
    }

    @Test
    void whenGetAllLinksThenReturnList() {
        List<Link> links = this.linkService.getAll();
        Assert.assertNotNull(links);
        Assert.assertThat(links.size(), is(2));

    }

    @Test
    void whenFindLinkByLinkThenReturnLink() {
        Link link = this.linkService.findByLink("link/2");
        Assert.assertNotNull(link);
        Assert.assertThat(link.getLinkCode(), is("X5vumU"));
    }

    @Test
    void whenFindLinkByLinkCodeThenReturnLink() {
        Link link = this.linkService.findByLinkCode("X5vumU");
        Assert.assertNotNull(link);
        Assert.assertThat(link.getId(), is(12L));
    }

    @Test
    void whenFindLinkByIdThenReturnLink() {
        Link link1 = this.linkService.findById(11L);
        Assert.assertNotNull(link1);
        Assert.assertThat(link1.getLink(), is("link/1"));
    }

    @Test
    void whebDeleteLinkThenSetLinkStatusDelete() {
        this.linkService.delete(11L);
        Link link = this.linkService.findById(11L);
        Assert.assertNotNull(link);
        Assert.assertThat(link.getStatus(), is(Status.DELETE));
    }
}