package com.laboratoriovi.reservas.dto;

import com.laboratoriovi.reservas.model.EstadoReserva;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservaRequest(
        @NotBlank(message = "El nombre del cliente es obligatorio")
        String nombreCliente,

        @NotBlank(message = "La habitación es obligatoria")
        String habitacion,

        @NotNull(message = "La fecha de entrada es obligatoria")
        LocalDate fechaEntrada,

        @NotNull(message = "La fecha de salida es obligatoria")
        LocalDate fechaSalida,

        @NotNull(message = "El estado es obligatorio")
        EstadoReserva estado
) {
}

