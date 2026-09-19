package com.laboratoriovi.reservas.repository;

import com.laboratoriovi.reservas.model.EstadoReserva;
import com.laboratoriovi.reservas.model.Reserva;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReservaRepository {

    private final List<Reserva> reservas = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public synchronized Reserva guardar(Reserva reserva) {
        reserva.setId(secuencia.getAndIncrement());
        reservas.add(reserva);
        return reserva;
    }

    public synchronized List<Reserva> buscarTodas() {
        return new ArrayList<>(reservas);
    }

    public synchronized Optional<Reserva> buscarPorId(Long id) {
        return reservas.stream()
                .filter(reserva -> reserva.getId().equals(id))
                .findFirst();
    }

    public synchronized boolean existeCruceDeFechas(String habitacion,
                                                     LocalDate fechaEntrada,
                                                     LocalDate fechaSalida,
                                                     Long idExcluido) {
        return reservas.stream()
                .filter(reserva -> reserva.getEstado() != EstadoReserva.CANCELADA)
                .filter(reserva -> reserva.getHabitacion().equalsIgnoreCase(habitacion))
                .filter(reserva -> idExcluido == null || !reserva.getId().equals(idExcluido))
                .anyMatch(reserva -> fechaEntrada.isBefore(reserva.getFechaSalida())
                        && fechaSalida.isAfter(reserva.getFechaEntrada()));
    }
}

