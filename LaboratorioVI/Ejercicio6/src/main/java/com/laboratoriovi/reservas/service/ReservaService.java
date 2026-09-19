package com.laboratoriovi.reservas.service;

import com.laboratoriovi.reservas.dto.ReservaRequest;
import com.laboratoriovi.reservas.exception.ConflictoException;
import com.laboratoriovi.reservas.exception.RecursoNoEncontradoException;
import com.laboratoriovi.reservas.exception.SolicitudInvalidaException;
import com.laboratoriovi.reservas.model.EstadoReserva;
import com.laboratoriovi.reservas.model.Reserva;
import com.laboratoriovi.reservas.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva crear(ReservaRequest request) {
        validarSolicitud(request, null);
        Reserva reserva = new Reserva(
                null,
                request.nombreCliente(),
                request.habitacion(),
                request.fechaEntrada(),
                request.fechaSalida(),
                request.estado()
        );
        return reservaRepository.guardar(reserva);
    }

    public List<Reserva> consultarTodas() {
        return reservaRepository.buscarTodas();
    }

    public Reserva consultarPorId(Long id) {
        return reservaRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Reserva no encontrada"));
    }

    public Reserva actualizar(Long id, ReservaRequest request) {
        Reserva reserva = consultarPorId(id);
        validarSolicitud(request, id);

        reserva.setNombreCliente(request.nombreCliente());
        reserva.setHabitacion(request.habitacion());
        reserva.setFechaEntrada(request.fechaEntrada());
        reserva.setFechaSalida(request.fechaSalida());
        reserva.setEstado(request.estado());
        return reserva;
    }

    public Reserva cancelar(Long id) {
        Reserva reserva = consultarPorId(id);
        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new ConflictoException("La reserva ya se encuentra cancelada");
        }
        reserva.setEstado(EstadoReserva.CANCELADA);
        return reserva;
    }

    private void validarSolicitud(ReservaRequest request, Long idExcluido) {
        if (!request.fechaSalida().isAfter(request.fechaEntrada())) {
            throw new SolicitudInvalidaException(
                    "La fecha de salida debe ser posterior a la fecha de entrada"
            );
        }

        if (request.estado() != EstadoReserva.CANCELADA
                && reservaRepository.existeCruceDeFechas(
                request.habitacion(),
                request.fechaEntrada(),
                request.fechaSalida(),
                idExcluido)) {
            throw new ConflictoException(
                    "La habitación ya está reservada para las fechas indicadas"
            );
        }
    }
}

