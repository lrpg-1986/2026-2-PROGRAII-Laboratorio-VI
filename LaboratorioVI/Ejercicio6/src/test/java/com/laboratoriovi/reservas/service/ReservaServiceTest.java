package com.laboratoriovi.reservas.service;

import com.laboratoriovi.reservas.dto.ReservaRequest;
import com.laboratoriovi.reservas.exception.ConflictoException;
import com.laboratoriovi.reservas.exception.RecursoNoEncontradoException;
import com.laboratoriovi.reservas.exception.SolicitudInvalidaException;
import com.laboratoriovi.reservas.model.EstadoReserva;
import com.laboratoriovi.reservas.model.Reserva;
import com.laboratoriovi.reservas.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservaServiceTest {

    private ReservaService reservaService;

    @BeforeEach
    void preparar() {
        reservaService = new ReservaService(new ReservaRepository());
    }

    @Test
    void debeCrearYConsultarUnaReserva() {
        Reserva reserva = reservaService.crear(crearRequest("204", "2026-10-15", "2026-10-18"));

        assertEquals(1L, reserva.getId());
        assertEquals(1, reservaService.consultarTodas().size());
        assertEquals("Ana López", reservaService.consultarPorId(1L).getNombreCliente());
    }

    @Test
    void debeActualizarUnaReserva() {
        Reserva reserva = reservaService.crear(crearRequest("204", "2026-10-15", "2026-10-18"));
        ReservaRequest cambios = crearRequest("305", "2026-11-01", "2026-11-04");

        Reserva actualizada = reservaService.actualizar(reserva.getId(), cambios);

        assertEquals("305", actualizada.getHabitacion());
        assertEquals(LocalDate.parse("2026-11-04"), actualizada.getFechaSalida());
    }

    @Test
    void debeCancelarUnaReserva() {
        Reserva reserva = reservaService.crear(crearRequest("204", "2026-10-15", "2026-10-18"));

        Reserva cancelada = reservaService.cancelar(reserva.getId());

        assertEquals(EstadoReserva.CANCELADA, cancelada.getEstado());
    }

    @Test
    void debeRechazarUnaSegundaCancelacion() {
        Reserva reserva = reservaService.crear(crearRequest("204", "2026-10-15", "2026-10-18"));
        reservaService.cancelar(reserva.getId());

        assertThrows(ConflictoException.class, () -> reservaService.cancelar(reserva.getId()));
    }

    @Test
    void debeRechazarFechasInvalidas() {
        ReservaRequest request = crearRequest("204", "2026-10-18", "2026-10-15");

        assertThrows(SolicitudInvalidaException.class, () -> reservaService.crear(request));
    }

    @Test
    void debeRechazarReservasCruzadasEnLaMismaHabitacion() {
        reservaService.crear(crearRequest("204", "2026-10-15", "2026-10-18"));
        ReservaRequest cruzada = crearRequest("204", "2026-10-17", "2026-10-20");

        assertThrows(ConflictoException.class, () -> reservaService.crear(cruzada));
    }

    @Test
    void debePermitirReservarDespuesDeCancelar() {
        Reserva reserva = reservaService.crear(crearRequest("204", "2026-10-15", "2026-10-18"));
        reservaService.cancelar(reserva.getId());

        Reserva nueva = reservaService.crear(crearRequest("204", "2026-10-15", "2026-10-18"));

        assertEquals(2L, nueva.getId());
    }

    @Test
    void debeInformarCuandoNoEncuentraLaReserva() {
        assertThrows(RecursoNoEncontradoException.class,
                () -> reservaService.consultarPorId(99L));
    }

    private ReservaRequest crearRequest(String habitacion, String entrada, String salida) {
        return new ReservaRequest(
                "Ana López",
                habitacion,
                LocalDate.parse(entrada),
                LocalDate.parse(salida),
                EstadoReserva.CONFIRMADA
        );
    }
}
