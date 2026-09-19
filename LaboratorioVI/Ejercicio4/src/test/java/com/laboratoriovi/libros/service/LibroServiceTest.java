package com.laboratoriovi.libros.service;

import com.laboratoriovi.libros.dto.LibroRequest;
import com.laboratoriovi.libros.exception.ConflictoException;
import com.laboratoriovi.libros.exception.RecursoNoEncontradoException;
import com.laboratoriovi.libros.model.EstadoLibro;
import com.laboratoriovi.libros.model.Libro;
import com.laboratoriovi.libros.repository.LibroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibroServiceTest {

    private LibroService libroService;

    @BeforeEach
    void preparar() {
        libroService = new LibroService(new LibroRepository());
    }

    @Test
    void debeRegistrarYConsultarUnLibro() {
        Libro libro = libroService.registrar(crearRequest("Cien años de soledad", "978-0307474728"));

        assertEquals(1L, libro.getId());
        assertEquals(1, libroService.consultarTodos().size());
    }

    @Test
    void debeBuscarLibrosPorTituloParcial() {
        libroService.registrar(crearRequest("Cien años de soledad", "978-0307474728"));

        List<Libro> resultado = libroService.consultarPorTitulo("soledad");

        assertEquals(1, resultado.size());
    }

    @Test
    void debeActualizarUnLibro() {
        Libro libro = libroService.registrar(crearRequest("Título inicial", "1234567890"));
        LibroRequest cambios = crearRequest("Título actualizado", "1234567890");

        Libro actualizado = libroService.actualizar(libro.getId(), cambios);

        assertEquals("Título actualizado", actualizado.getTitulo());
    }

    @Test
    void debeEliminarUnLibro() {
        Libro libro = libroService.registrar(crearRequest("Libro temporal", "1234567890"));

        libroService.eliminar(libro.getId());

        assertTrue(libroService.consultarTodos().isEmpty());
    }

    @Test
    void debeRechazarIsbnDuplicado() {
        libroService.registrar(crearRequest("Primer libro", "1234567890"));

        assertThrows(ConflictoException.class,
                () -> libroService.registrar(crearRequest("Segundo libro", "1234567890")));
    }

    @Test
    void debeInformarCuandoNoEncuentraElTitulo() {
        assertThrows(RecursoNoEncontradoException.class,
                () -> libroService.consultarPorTitulo("inexistente"));
    }

    private LibroRequest crearRequest(String titulo, String isbn) {
        return new LibroRequest(
                titulo,
                "Autor de prueba",
                isbn,
                2026,
                EstadoLibro.DISPONIBLE
        );
    }
}
