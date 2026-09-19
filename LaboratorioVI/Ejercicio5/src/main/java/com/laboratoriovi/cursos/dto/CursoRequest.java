package com.laboratoriovi.cursos.dto;

import com.laboratoriovi.cursos.model.EstadoCurso;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El código es obligatorio")
        String codigo,

        @NotNull(message = "Los créditos son obligatorios")
        @Min(value = 1, message = "Los créditos deben ser mayores que cero")
        Integer creditos,

        @NotNull(message = "El estado es obligatorio")
        EstadoCurso estado
) {
}

