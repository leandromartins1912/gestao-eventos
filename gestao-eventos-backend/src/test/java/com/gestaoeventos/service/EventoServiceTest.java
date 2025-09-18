package com.gestaoeventos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.gestaoeventos.dto.EventoDTO;
import com.gestaoeventos.exception.ResourceNotFoundException;
import com.gestaoeventos.model.Evento;
import com.gestaoeventos.repository.EventoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EventoServiceTest {

    @Mock
    private EventoRepository repository;

    @InjectMocks
    private EventoService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarEventoPorId() {
        Evento evento = new Evento();
        evento.setId(1L);
        evento.setDeleted(false);

        when(repository.findById(1L)).thenReturn(Optional.of(evento));

        EventoDTO dto = service.buscarPorId(1L);

        assertEquals(1L, dto.getId());
    }

    @Test
    void deveLancarExcecaoQuandoEventoNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void deveCriarNovoEvento() {
        EventoDTO dto = new EventoDTO();
        dto.setTitle("Evento Teste");

        Evento evento = new Evento();
        evento.setId(1L);
        evento.setTitle("Evento Teste");

        when(repository.save(any(Evento.class))).thenReturn(evento);

        EventoDTO resultado = service.criarEvento(dto);

        assertEquals("Evento Teste", resultado.getTitle());
    }
}
