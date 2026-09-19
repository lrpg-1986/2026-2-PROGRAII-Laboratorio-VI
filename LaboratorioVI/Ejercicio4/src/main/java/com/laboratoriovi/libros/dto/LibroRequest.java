package com.laboratoriovi.libros.dto;

import com.laboratoriovi.libros.model.EstadoLibro;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LibroRequest(
        @NotBlank(message = "El título es obligatorio")
        String titulo,

        @NotBlank(message = "El autor es obligatorio")
        String autor,

        @NotBlank(message = "El ISBN es obligatorio")
        @Size(min = 10, max = 17, message = "El ISBN debe tener entre 10 y 17 caracteres")
        String isbn,

        @NotNull(message = "El año de publicación es obligatorio")
        @Min(value = 1, message = "El año de publicación debe ser mayor que cero")
        Integer anioPublicacion,

        @NotNull(message = "El estado es obligatorio")
        EstadoLibro estado
) {
}

