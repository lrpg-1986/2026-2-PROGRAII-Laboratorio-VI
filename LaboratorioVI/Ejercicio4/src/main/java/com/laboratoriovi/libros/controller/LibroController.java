package com.laboratoriovi.libros.controller;

import com.laboratoriovi.libros.dto.LibroRequest;
import com.laboratoriovi.libros.model.Libro;
import com.laboratoriovi.libros.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<Libro> registrar(@Valid @RequestBody LibroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libroService.registrar(request));
    }

    @GetMapping
    public List<Libro> consultarTodos() {
        return libroService.consultarTodos();
    }

    @GetMapping("/titulo/{titulo}")
    public List<Libro> consultarPorTitulo(@PathVariable String titulo) {
        return libroService.consultarPorTitulo(titulo);
    }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id,
                            @Valid @RequestBody LibroRequest request) {
        return libroService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

