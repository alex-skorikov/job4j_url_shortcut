package com.skorikov.job4jurlshortcut.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

@TestPropertySource("/application.test.properties")
@Sql(value = {"/create_site_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_site_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class LinkServiceImplTest {

    @Autowired
    private LinkServiceImpl linkService;

    @Test
    void joinLinkToSite() {
    }

    @Test
    void getAll() {
    }

    @Test
    void findByLink() {
    }

    @Test
    void findByLinkCode() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }
}