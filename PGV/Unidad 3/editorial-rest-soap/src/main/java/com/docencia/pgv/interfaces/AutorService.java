package com.docencia.pgv.interfaces;

import com.docencia.pgv.modelo.Autor;

import java.util.List;

public interface AutorService {
    List<Autor> findAll();

    Autor findByIdOrThrow(Long id);

    Autor create(Autor autor);

    void delete(Long id);
}
