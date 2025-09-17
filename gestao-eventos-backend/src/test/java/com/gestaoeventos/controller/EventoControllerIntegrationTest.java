package com.gestaoeventos.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Page;

import com.gestaoeventos.dto.EventoDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventoControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void deveriaCriarEListarEventos() {
        String baseUrl = "http://localhost:" + port + "/api/events";

        EventoDTO novoEvento = new EventoDTO();
        novoEvento.setTitle("Teste");
        novoEvento.setDescription("Descrição");
        novoEvento.setLocation("Local");
        novoEvento.setEventDatetime(LocalDateTime.now().plusDays(1));

        ResponseEntity<EventoDTO> response = restTemplate.postForEntity(baseUrl, novoEvento, EventoDTO.class);
        assertEquals(201, response.getStatusCodeValue());

        ResponseEntity<Page<EventoDTO>> pageResponse = restTemplate.getForEntity(baseUrl + "?page=0&size=10", (Class<Page<EventoDTO>>)(Object)Page.class);
        assertTrue(pageResponse.getBody().getContent().size() > 0);
    }
}
