package com.docencia.rest.repository;

import com.docencia.rest.model.DetalleProductoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DetalleProductoRepository extends MongoRepository<DetalleProductoDocument, Integer> {
    DetalleProductoDocument findByProductoId(int productoId);
    boolean deleteByProductoId(int productoId);
}
