package com.docencia.rest.service;

import com.docencia.rest.domain.Producto;
import com.docencia.rest.mapper.DetalleProductoMapper;
import com.docencia.rest.mapper.ProductoMapper;
import com.docencia.rest.model.DetalleProductoDocument;
import com.docencia.rest.model.ProductoEntity;
import com.docencia.rest.repository.DetalleProductoRepository;
import com.docencia.rest.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService implements ProductoServiceInterface {

    private final ProductoRepository productoRepository;
    private final DetalleProductoRepository detalleProductoRepository;
    private final ProductoMapper productoMapper;
    private final DetalleProductoMapper detalleProductoMapper;

    public ProductoService(ProductoRepository productoRepository, DetalleProductoRepository detalleProductoRepository, ProductoMapper productoMapper, DetalleProductoMapper detalleProductoMapper) {
        this.productoRepository = productoRepository;
        this.detalleProductoRepository = detalleProductoRepository;
        this.productoMapper = productoMapper;
        this.detalleProductoMapper = detalleProductoMapper;
    }

    @Override
    public Optional<Producto> find(Producto producto) {
        if (producto == null) return Optional.empty();
        return findById(producto.getId());
    }

    @Override
    public Optional<Producto> findById(int id) {
        Optional<ProductoEntity> entityOpt = productoRepository.findById(id);
        if (entityOpt.isEmpty()) {
            return Optional.empty();
        }

        ProductoEntity entity = entityOpt.get();
        DetalleProductoDocument detalleDoc = detalleProductoRepository.findByProductoId(id);

        Producto producto = productoMapper.toDomain(entity, detalleDoc);
        return Optional.of(producto);
    }

    @Override
    public List<Producto> findAll() {
        List<ProductoEntity> entities = productoRepository.findAll();

        return entities.stream()
                .map(entity -> {
                    DetalleProductoDocument detalleDoc =
                            detalleProductoRepository.findByProductoId(entity.getId());
                    return productoMapper.toDomain(entity, detalleDoc);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Producto save(Producto producto) {
        // 1. Guardar en BD relacional
        ProductoEntity entityToSave = productoMapper.toEntity(producto);
        ProductoEntity savedEntity = productoRepository.save(entityToSave);

        // 2. Guardar detalle en Mongo (si existe)
        if (producto.getDetalleProducto() != null) {
            DetalleProductoDocument detalleDoc =
                    detalleProductoMapper.toDocument(producto.getDetalleProducto());
            detalleDoc.setProductoId(savedEntity.getId());
            detalleProductoRepository.save(detalleDoc);
        }

        // 3. Reconstruir dominio completo
        DetalleProductoDocument detallePersistido =
                detalleProductoRepository.findByProductoId(savedEntity.getId());

        return productoMapper.toDomain(savedEntity, detallePersistido);
    }

    @Override
    public boolean delete(Producto producto) {
        if (producto == null) {
            return false;
        }
        return deleteById(producto.getId());
    }

    @Override
    public boolean deleteById(int id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }

        productoRepository.deleteById(id);
        detalleProductoRepository.deleteByProductoId(id);

        return true;
    }
}
