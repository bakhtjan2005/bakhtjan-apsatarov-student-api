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
class StudentApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createStudentReturns201() throws Exception {
        String body = "{\"fullName\":\"Bakhtjan Apsatarov\",\"email\":\"bakhtjan-apsatarov@example.com\",\"course\":\"DevOps\"}";
        mockMvc.perform(post("/api/students").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName").value("Bakhtjan Apsatarov"));
    }

    @Test
    void getAllStudentsReturns200() throws Exception {
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk());
    }

    @Test
    void getNonexistentStudentReturns404() throws Exception {
        mockMvc.perform(get("/api/students/999999"))
                .andExpect(status().isNotFound()); // EXPECT-404
    }
}
