package com.docencia.rest.service;

import com.docencia.rest.model.Producto;
import com.docencia.rest.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements ProductoServiceInterface {

    private ProductoRepository productoRepository;

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Optional<Producto> findBy(Producto producto) {
        return findById(producto.getId());
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public void delete(Producto producto) {
        productoRepository.delete(producto);
    }
}
