package com.gestaoeventos.controller;

import com.gestaoeventos.model.Evento;
import com.gestaoeventos.repository.EventoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class EventoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventoRepository repository;

    @BeforeEach
    void setup() {
        Evento evento = new Evento();
        evento.setTitle("Evento Integração");
        evento.setDeleted(false);
        repository.save(evento);
    }

    @Test
    void deveRetornarEventoPorId() throws Exception {
        mockMvc.perform(get("/api/events/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Evento Integração"));
    }
}
