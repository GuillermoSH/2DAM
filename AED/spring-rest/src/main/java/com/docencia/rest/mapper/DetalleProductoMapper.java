package com.docencia.rest.mapper;

import org.mapstruct.Mapper;

import com.docencia.rest.domain.DetalleProducto;
import com.docencia.rest.model.DetalleProductoDocument;

@Mapper(componentModel = "spring")
public interface DetalleProductoMapper {

    // Dominio -> Mongo
    DetalleProductoDocument toDocument(DetalleProducto source);

    // Mongo -> Dominio
    DetalleProducto toDocument(DetalleProductoDocument source);
}
