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

   
}
