package com.example.demo.controller;

import com.example.demo.dto.Trainee;
import com.example.demo.repository.TraineeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TraineeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    TraineeRepository traineeRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void should_get_all_trainees() throws Exception {
        mockMvc.perform(get("/trainees?grouped=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(14)));
    }

    @Test
    void should_create_trainee_success() throws Exception {
        String json = objectMapper.writeValueAsString(Trainee.builder().name("qindi").build());
        mockMvc.perform(post("/trainees?grouped=false").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("qindi")));
    }

    @Test
    void should_get_exception_when_trainee_invalid() throws Exception {
        String json = objectMapper.writeValueAsString(Trainee.builder().build());
        mockMvc.perform(post("/trainees?grouped=false").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_delete_trainee_when_id_exist() throws Exception {
        mockMvc.perform(delete("/trainees/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_delete_trainee_failed_and_throw_exception_when_id_not_exist() throws Exception {
        mockMvc.perform(delete("/trainees/0"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("学员不存在", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
