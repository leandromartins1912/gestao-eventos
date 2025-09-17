package com.gestaoeventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestaoeventos.dto.EventoDTO;
import com.gestaoeventos.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventoController {

    @Autowired
    private EventoService service;

    @Operation(summary = "Lista eventos com paginação", description = "Lista eventos com paginação")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "Eventos listados com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @GetMapping
    public Page<EventoDTO> getEventos(@RequestParam int page, @RequestParam int size
    ) {
        return service.listarEventos(page, size);
    }

    @Operation(summary = "Busca evento por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evento encontrado"),
        @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    @GetMapping("/{id}")
    public EventoDTO getEvento(
        @Parameter(description = "ID do evento", required = true) @PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @Operation(summary = "Cria um novo evento")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Evento criado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public EventoDTO createEvento(
        @Parameter(description = "Dados do evento", required = true) @Valid @RequestBody EventoDTO dto) {
        return service.criarEvento(dto);
    }

    @Operation(summary = "Atualiza um evento existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evento atualizado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    @PutMapping("/{id}")
    public EventoDTO updateEvento(
        @Parameter(description = "ID do evento", required = true) @PathVariable Long id,
        @Parameter(description = "Dados do evento", required = true) @Valid @RequestBody EventoDTO dto) {
        return service.atualizarEvento(id, dto);
    }

    @Operation(summary = "Remove um evento")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Evento removido"),
        @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(
        @Parameter(description = "ID do evento", required = true) @PathVariable Long id) {
        service.removerEvento(id);
        return ResponseEntity.noContent().build();
    }
}
