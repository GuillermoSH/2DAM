package com.docencia.pgv.interfaces;

import com.docencia.pgv.modelo.Libro;

import java.util.List;

public interface LibroService {
    List<Libro> findAll();

    Libro findByIdOrThrow(Long id);

    List<Libro> findByAutorOrThrow(Long idAutor);

    Libro create(Libro libro);

    void delete(Long id);
}
