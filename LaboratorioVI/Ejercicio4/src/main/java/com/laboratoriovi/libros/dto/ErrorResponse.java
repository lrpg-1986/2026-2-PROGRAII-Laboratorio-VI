package com.laboratoriovi.libros.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime fecha,
        int estado,
        String mensaje
) {
}

