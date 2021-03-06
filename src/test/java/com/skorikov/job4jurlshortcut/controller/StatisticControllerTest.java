package com.skorikov.job4jurlshortcut.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

/**
 * Statistic controller test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@AutoConfigureMockMvc
@TestPropertySource("/application.test.properties")
@Sql(value = {"/create_site_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_site_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails(value = "site_1")
class StatisticControllerTest {
    /**
     * Mock mvc.
     */
    @Autowired
    private MockMvc mockMvc;
    /**
     * Controller.
     */
    @Autowired
    private StatisticController statisticController;

    /**
     * Controller isnot null.
     */
    @Test
    void controllerIsNotNull() {
        assertThat(statisticController).isNotNull();
    }

    /**
     * Test get link from DB by code.
     *
     * @throws Exception exception.
     */
    @Test
    void testRedirect() throws Exception {
        this.mockMvc.perform(get("/redirect/").contentType(MediaType.APPLICATION_JSON)
                .param("code", "IreGCc"))
                .andDo(print())
                .andExpect(header().string("REDIRECT", "link/1"))
                .andExpect(status().is(302));
    }

    /**
     * Get statistics from DB.
     *
     * @throws Exception exception.
     */
    @Test
    void testGetStatistic() throws Exception {
        this.mockMvc.perform(post("/statistic").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].total").value(3))
                .andExpect(jsonPath("$[0].url").value("link/1"))
                .andExpect(status().isOk());
    }
}