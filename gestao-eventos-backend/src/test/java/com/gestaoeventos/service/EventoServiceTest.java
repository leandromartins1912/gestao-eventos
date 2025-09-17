package com.gestaoeventos.service;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.gestaoeventos.dto.EventoDTO;
import com.gestaoeventos.model.Evento;
import com.gestaoeventos.repository.EventoRepository;

@SpringBootTest
public class EventoServiceTest {

	@Mock
    private EventoRepository repository;

    @InjectMocks
    private EventoService service;

    @Test
    public void deveriaRetornarEventoPorId() {
        Evento evento = new Evento(1L, "Titulo", "Descrição", LocalDateTime.now());
        when(repository.findById(1L)).thenReturn(Optional.of(evento));

        EventoDTO dto = service.buscarPorId(1L);

        assertNotNull(dto);
        assertEquals("Titulo", dto.getTitle());
        verify(repository).findById(1L);
    }
}
