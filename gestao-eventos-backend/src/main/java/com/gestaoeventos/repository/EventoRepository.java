package com.gestaoeventos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestaoeventos.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    @Query("SELECT e FROM Evento e WHERE e.deleted = false")
    Page<Evento> findAllActive(Pageable pageable);
}
