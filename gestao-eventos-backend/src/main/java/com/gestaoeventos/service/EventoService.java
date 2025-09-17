package com.gestaoeventos.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gestaoeventos.dto.EventoDTO;
import com.gestaoeventos.exception.ResourceNotFoundException;
import com.gestaoeventos.model.Evento;
import com.gestaoeventos.repository.EventoRepository;

@Service
public class EventoService {
	@Autowired
    private EventoRepository repository;

    public Page<EventoDTO> listarEventos(int page, int size) {
        Page<Evento> eventos = repository.findAllActive(PageRequest.of(page, size));
        return eventos.map(this::toDTO);
    }

    public EventoDTO buscarPorId(Long id) {
        Evento evento = repository.findById(id)
            .filter(e -> !e.getDeleted())
            .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));
        return toDTO(evento);
    }

    public EventoDTO criarEvento(EventoDTO dto) {    	
        Evento evento = toEntity(dto);
        evento.setDeleted(false);        
        return toDTO(repository.save(evento));
    }

    public EventoDTO atualizarEvento(Long id, EventoDTO dto) {
    	
        Evento evento = repository.findById(id)
            .filter(e -> !e.getDeleted())
            .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));
        evento.setTitle(dto.getTitle());
        evento.setDescription(dto.getDescription());
        evento.setEventDatetime(dto.getEventDatetime());
        evento.setLocation(dto.getLocation());       
        return toDTO(repository.save(evento));
    }

    public void removerEvento(Long id) {
        Evento evento = repository.findById(id)
            .filter(e -> !e.getDeleted())
            .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));
        evento.setDeleted(true);
        repository.save(evento);
    }
    
    private EventoDTO toDTO(Evento e) { 
    	EventoDTO dto = new EventoDTO();
        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setDescription(e.getDescription());
        dto.setEventDatetime(e.getEventDatetime());
        dto.setLocation(e.getLocation());
        dto.setCreatedAt(e.getCreatedAt());  
        dto.setUpdatedAt(e.getUpdatedAt());  
        return dto;
    }
    private Evento toEntity(EventoDTO dto) {
    	 Evento e = new Evento();
	    e.setId(dto.getId()); 
	    e.setTitle(dto.getTitle());
	    e.setDescription(dto.getDescription());
	    e.setEventDatetime(dto.getEventDatetime());
	    e.setLocation(dto.getLocation());
	    dto.setCreatedAt(e.getCreatedAt()); 
	    dto.setUpdatedAt(e.getUpdatedAt()); 
	    return e;    	
    }
}
