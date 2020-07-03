package com.skorikov.job4jurlshortcut.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests registration controller.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@AutoConfigureMockMvc
@TestPropertySource("/application.test.properties")
@Sql(value = {"/create_site_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_site_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class RegistrationControllerTest {
    /**
     * Mock mvc.
     */
    @Autowired
    private MockMvc mockMvc;
    /**
     * Controller.
     */
    @Autowired
    private RegistrationController registrationController;

    /**
     * Controller is not null.
     */
    @Test
    void registrationControllerIsNotNull() {
        assertThat(registrationController).isNotNull();
    }

    /**
     * Test registration new site.
     * @throws Exception exception.
     */
    @Test
    void testRegistrationNewSite() throws Exception {

        this.mockMvc.perform(post("/registration").contentType(MediaType.APPLICATION_JSON)
                .content("{\"site\" : \"newSite\"}"))
                .andExpect(jsonPath("$.login").value("newSite"))
                .andExpect(jsonPath("$.registration").value("true"))
                .andExpect(jsonPath("$.password").isNotEmpty())
                .andExpect(status().isOk())
                .andDo(print());

    }

    /**
     * Test try registration dublicate dite.
     * @throws Exception exception.
     */
    @Test
    void testRegistrationDublicateSite() throws Exception {
        this.mockMvc.perform(post("/registration").contentType(MediaType.APPLICATION_JSON)
                .content("{\"site\" : \"site_1\"}"))
                .andExpect(jsonPath("$.registration").value("false"))
                .andExpect(status().isOk())
                .andDo(print());

    }
}