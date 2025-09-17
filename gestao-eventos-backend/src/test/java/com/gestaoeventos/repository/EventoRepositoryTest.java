package com.gestaoeventos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gestaoeventos.model.Evento;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

@DataJpaTest
public class EventoRepositoryTest {

	@Autowired
    private EventoRepository repository;

    @Test
    public void deveriaSalvarEEncontrarEvento() {
        Evento evento = new Evento(null, "Titulo", "Descrição", LocalDateTime.now());
        repository.save(evento);

        assertNotNull(evento.getId());
        assertTrue(repository.findById(evento.getId()).isPresent());
    }
}
