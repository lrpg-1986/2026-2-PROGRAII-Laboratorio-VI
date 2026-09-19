package com.laboratoriovi.libros.repository;

import com.laboratoriovi.libros.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LibroRepository {

    private final List<Libro> libros = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public synchronized Libro guardar(Libro libro) {
        libro.setId(secuencia.getAndIncrement());
        libros.add(libro);
        return libro;
    }

    public synchronized List<Libro> buscarTodos() {
        return new ArrayList<>(libros);
    }

    public synchronized Optional<Libro> buscarPorId(Long id) {
        return libros.stream()
                .filter(libro -> libro.getId().equals(id))
                .findFirst();
    }

    public synchronized List<Libro> buscarPorTitulo(String titulo) {
        String tituloBuscado = titulo.toLowerCase(Locale.ROOT);
        return libros.stream()
                .filter(libro -> libro.getTitulo().toLowerCase(Locale.ROOT).contains(tituloBuscado))
                .toList();
    }

    public synchronized boolean existeIsbn(String isbn, Long idExcluido) {
        return libros.stream()
                .anyMatch(libro -> libro.getIsbn().equalsIgnoreCase(isbn)
                        && (idExcluido == null || !libro.getId().equals(idExcluido)));
    }

    public synchronized void eliminar(Libro libro) {
        libros.remove(libro);
    }
}

