package com.docencia.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.docencia.rest.domain.Producto;
import com.docencia.rest.model.DetalleProductoDocument;
import com.docencia.rest.model.ProductoEntity;

@Mapper(componentModel = "spring", uses = { DetalleProductoMapper.class })
public interface ProductoMapper {

    // Dominio -> JPA
    ProductoEntity toEntity(Producto source);

    // JPA -> Dominio (sin detalle)
    Producto toDomain(ProductoEntity source);

    // JPA + Mongo -> Dominio completo
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "detalleProducto", source = "detalle")
    Producto toDomain(ProductoEntity entity, DetalleProductoDocument detalle);
}
