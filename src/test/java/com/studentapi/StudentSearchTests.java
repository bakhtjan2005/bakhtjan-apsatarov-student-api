package com.studentapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StudentSearchTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void searchByNameReturnsMatchingStudent() throws Exception {
        String body = "{\"fullName\":\"Zzz Searchable Bakhtjan Apsatarov\",\"email\":\"zzz@example.com\",\"course\":\"DevOps\"}";
        mockMvc.perform(post("/api/students").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/students/search").param("name", "zzz searchable"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("Zzz Searchable Bakhtjan Apsatarov"));
    }
}
