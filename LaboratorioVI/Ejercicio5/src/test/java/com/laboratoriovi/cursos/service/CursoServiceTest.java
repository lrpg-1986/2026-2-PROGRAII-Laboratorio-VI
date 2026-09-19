package com.laboratoriovi.cursos.service;

import com.laboratoriovi.cursos.dto.CursoRequest;
import com.laboratoriovi.cursos.exception.ConflictoException;
import com.laboratoriovi.cursos.exception.RecursoNoEncontradoException;
import com.laboratoriovi.cursos.model.Curso;
import com.laboratoriovi.cursos.model.EstadoCurso;
import com.laboratoriovi.cursos.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CursoServiceTest {

    private CursoService cursoService;

    @BeforeEach
    void preparar() {
        cursoService = new CursoService(new CursoRepository());
    }

    @Test
    void debeCrearYConsultarUnCurso() {
        Curso curso = cursoService.crear(crearRequest("Programación II", "PROG-201"));

        assertEquals(1L, curso.getId());
        assertEquals(1, cursoService.consultarTodos().size());
    }

    @Test
    void debeBuscarCursoPorCodigo() {
        cursoService.crear(crearRequest("Programación II", "PROG-201"));

        Curso encontrado = cursoService.consultarPorCodigo("prog-201");

        assertEquals("Programación II", encontrado.getNombre());
    }

    @Test
    void debeActualizarUnCurso() {
        Curso curso = cursoService.crear(crearRequest("Curso inicial", "CUR-101"));
        CursoRequest cambios = new CursoRequest("Curso actualizado", "CUR-101", 5, EstadoCurso.INACTIVO);

        Curso actualizado = cursoService.actualizar(curso.getId(), cambios);

        assertEquals("Curso actualizado", actualizado.getNombre());
        assertEquals(EstadoCurso.INACTIVO, actualizado.getEstado());
    }

    @Test
    void debeEliminarUnCurso() {
        Curso curso = cursoService.crear(crearRequest("Curso temporal", "CUR-101"));

        cursoService.eliminar(curso.getId());

        assertTrue(cursoService.consultarTodos().isEmpty());
    }

    @Test
    void debeRechazarCodigoDuplicado() {
        cursoService.crear(crearRequest("Primer curso", "CUR-101"));

        assertThrows(ConflictoException.class,
                () -> cursoService.crear(crearRequest("Segundo curso", "cur-101")));
    }

    @Test
    void debeInformarCuandoNoEncuentraElCodigo() {
        assertThrows(RecursoNoEncontradoException.class,
                () -> cursoService.consultarPorCodigo("NO-EXISTE"));
    }

    private CursoRequest crearRequest(String nombre, String codigo) {
        return new CursoRequest(nombre, codigo, 4, EstadoCurso.ACTIVO);
    }
}
