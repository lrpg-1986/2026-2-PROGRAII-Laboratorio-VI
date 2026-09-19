package com.laboratoriovi.reservas.controller;

import com.laboratoriovi.reservas.dto.ReservaRequest;
import com.laboratoriovi.reservas.model.Reserva;
import com.laboratoriovi.reservas.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@Valid @RequestBody ReservaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crear(request));
    }

    @GetMapping
    public List<Reserva> consultarTodas() {
        return reservaService.consultarTodas();
    }

    @GetMapping("/{id}")
    public Reserva consultarPorId(@PathVariable Long id) {
        return reservaService.consultarPorId(id);
    }

    @PutMapping("/{id}")
    public Reserva actualizar(@PathVariable Long id,
                              @Valid @RequestBody ReservaRequest request) {
        return reservaService.actualizar(id, request);
    }

    @PatchMapping("/{id}/cancelacion")
    public Reserva cancelar(@PathVariable Long id) {
        return reservaService.cancelar(id);
    }
}

