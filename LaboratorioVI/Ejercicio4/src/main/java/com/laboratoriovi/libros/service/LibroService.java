package com.laboratoriovi.libros.service;

import com.laboratoriovi.libros.dto.LibroRequest;
import com.laboratoriovi.libros.exception.ConflictoException;
import com.laboratoriovi.libros.exception.RecursoNoEncontradoException;
import com.laboratoriovi.libros.model.Libro;
import com.laboratoriovi.libros.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro registrar(LibroRequest request) {
        validarIsbnDisponible(request.isbn(), null);
        Libro libro = new Libro(
                null,
                request.titulo(),
                request.autor(),
                request.isbn(),
                request.anioPublicacion(),
                request.estado()
        );
        return libroRepository.guardar(libro);
    }

    public List<Libro> consultarTodos() {
        return libroRepository.buscarTodos();
    }

    public List<Libro> consultarPorTitulo(String titulo) {
        List<Libro> libros = libroRepository.buscarPorTitulo(titulo);
        if (libros.isEmpty()) {
            throw new RecursoNoEncontradoException("No se encontraron libros con ese título");
        }
        return libros;
    }

    public Libro actualizar(Long id, LibroRequest request) {
        Libro libro = buscarPorId(id);
        validarIsbnDisponible(request.isbn(), id);

        libro.setTitulo(request.titulo());
        libro.setAutor(request.autor());
        libro.setIsbn(request.isbn());
        libro.setAnioPublicacion(request.anioPublicacion());
        libro.setEstado(request.estado());
        return libro;
    }

    public void eliminar(Long id) {
        Libro libro = buscarPorId(id);
        libroRepository.eliminar(libro);
    }

    private Libro buscarPorId(Long id) {
        return libroRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Libro no encontrado"));
    }

    private void validarIsbnDisponible(String isbn, Long idExcluido) {
        if (libroRepository.existeIsbn(isbn, idExcluido)) {
            throw new ConflictoException("Ya existe un libro con el mismo ISBN");
        }
    }
}

