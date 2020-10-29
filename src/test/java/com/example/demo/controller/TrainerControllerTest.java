package com.example.demo.controller;

import com.example.demo.dto.Trainee;
import com.example.demo.dto.Trainer;
import com.example.demo.repository.TrainerRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TrainerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    TrainerRepository trainerRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void should_get_all_trainers() throws Exception {
        mockMvc.perform(get("/trainers?grouped=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void should_create_trainer_success() throws Exception {
        String json = objectMapper.writeValueAsString(Trainer.builder().name("qindi").build());
        mockMvc.perform(post("/trainers?grouped=false").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("qindi")));
    }

    @Test
    void should_get_exception_when_trainer_invalid() throws Exception {
        String json = objectMapper.writeValueAsString(Trainer.builder().build());
        mockMvc.perform(post("/trainers?grouped=false").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_delete_trainer_when_id_exist() throws Exception {
        mockMvc.perform(delete("/trainers/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_delete_trainer_failed_and_throw_exception_when_id_not_exist() throws Exception {
        mockMvc.perform(delete("/trainers/0"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("讲师不存在", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
