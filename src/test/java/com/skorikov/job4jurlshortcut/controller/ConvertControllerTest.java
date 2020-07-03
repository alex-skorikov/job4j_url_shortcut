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

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests convert controller.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@AutoConfigureMockMvc
@TestPropertySource("/application.test.properties")
@Sql(value = {"/create_site_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create_site_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails(value = "site_1")
class ConvertControllerTest {
    /**
     * Mock mvc.
     */
    @Autowired
    private MockMvc mockMvc;
    /**
     * Controller.
     */
    @Autowired
    private ConvertController convertController;

    /**
     * Test controller is not null.
     */
    @Test
    void controllerIsNotNull() {
        assertThat(convertController).isNotNull();
    }

    /**
     * Convert controller test method POST.
     * @throws Exception exception.
     */
    @Test
    void testConvertControllerGetLinkCode() throws Exception {
        this.mockMvc.perform(post("/convert").contentType(MediaType.APPLICATION_JSON)
                .content("{\"url\" : \"testLink\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").isNotEmpty());
    }


}