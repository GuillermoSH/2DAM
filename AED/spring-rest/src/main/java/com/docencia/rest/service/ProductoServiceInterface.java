package com.docencia.rest.service;

import com.docencia.rest.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceInterface {
    Optional<Producto> findBy(Producto producto);

    Optional<Producto> findById(Integer id);

    List<Producto> findAll();

    Producto save(Producto producto);

    void deleteById(Integer id);

    void delete(Producto producto);
}
