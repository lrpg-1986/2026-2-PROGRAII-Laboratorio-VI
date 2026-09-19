package com.laboratoriovi.reservas.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime fecha,
        int estado,
        String mensaje
) {
}

