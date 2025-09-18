package com.gestaoeventos.repository;

import com.gestaoeventos.model.Evento;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EventoRepositoryTest {

    @Autowired
    private EventoRepository repository;

    @Test
    void deveSalvarERecuperarEventoAtivo() {
        Evento evento = new Evento();
        evento.setTitle("Evento Ativo");
        evento.setDeleted(false);
        repository.save(evento);

        var resultado = repository.findAllActive(PageRequest.of(0, 10));

        assertFalse(resultado.isEmpty());
        assertEquals("Evento Ativo", resultado.getContent().get(0).getTitle());
    }

    @Test
    void naoDeveRetornarEventoDeletado() {
        Evento evento = new Evento();
        evento.setTitle("Evento Deletado");
        evento.setDeleted(true);
        repository.save(evento);

        var resultado = repository.findAllActive(PageRequest.of(0, 10));

        assertTrue(resultado.isEmpty());
    }
}
