package com.laboratoriovi.cursos.repository;

import com.laboratoriovi.cursos.model.Curso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CursoRepository {

    private final List<Curso> cursos = new ArrayList<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public synchronized Curso guardar(Curso curso) {
        curso.setId(secuencia.getAndIncrement());
        cursos.add(curso);
        return curso;
    }

    public synchronized List<Curso> buscarTodos() {
        return new ArrayList<>(cursos);
    }

    public synchronized Optional<Curso> buscarPorId(Long id) {
        return cursos.stream()
                .filter(curso -> curso.getId().equals(id))
                .findFirst();
    }

    public synchronized Optional<Curso> buscarPorCodigo(String codigo) {
        return cursos.stream()
                .filter(curso -> curso.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public synchronized boolean existeCodigo(String codigo, Long idExcluido) {
        return cursos.stream()
                .anyMatch(curso -> curso.getCodigo().equalsIgnoreCase(codigo)
                        && (idExcluido == null || !curso.getId().equals(idExcluido)));
    }

    public synchronized void eliminar(Curso curso) {
        cursos.remove(curso);
    }
}

