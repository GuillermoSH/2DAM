package com.docencia.rest.repository;

import com.docencia.rest.model.DetalleProducto;

import java.util.Optional;

public interface DetalleProductoRepository {
    Optional<DetalleProducto> findByProductoId(int productoId);
    DetalleProducto save(int productoId, DetalleProducto detalle);
    boolean deleteByProductoId(int productoId);
}
