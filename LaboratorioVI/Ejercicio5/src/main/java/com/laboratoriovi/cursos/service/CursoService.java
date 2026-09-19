package com.laboratoriovi.cursos.service;

import com.laboratoriovi.cursos.dto.CursoRequest;
import com.laboratoriovi.cursos.exception.ConflictoException;
import com.laboratoriovi.cursos.exception.RecursoNoEncontradoException;
import com.laboratoriovi.cursos.model.Curso;
import com.laboratoriovi.cursos.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso crear(CursoRequest request) {
        validarCodigoDisponible(request.codigo(), null);
        Curso curso = new Curso(
                null,
                request.nombre(),
                request.codigo(),
                request.creditos(),
                request.estado()
        );
        return cursoRepository.guardar(curso);
    }

    public List<Curso> consultarTodos() {
        return cursoRepository.buscarTodos();
    }

    public Curso consultarPorCodigo(String codigo) {
        return cursoRepository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new RecursoNoEncontradoException("Curso no encontrado"));
    }

    public Curso actualizar(Long id, CursoRequest request) {
        Curso curso = buscarPorId(id);
        validarCodigoDisponible(request.codigo(), id);

        curso.setNombre(request.nombre());
        curso.setCodigo(request.codigo());
        curso.setCreditos(request.creditos());
        curso.setEstado(request.estado());
        return curso;
    }

    public void eliminar(Long id) {
        Curso curso = buscarPorId(id);
        cursoRepository.eliminar(curso);
    }

    private Curso buscarPorId(Long id) {
        return cursoRepository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Curso no encontrado"));
    }

    private void validarCodigoDisponible(String codigo, Long idExcluido) {
        if (cursoRepository.existeCodigo(codigo, idExcluido)) {
            throw new ConflictoException("Ya existe un curso con el mismo código");
        }
    }
}

