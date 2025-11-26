package com.docencia.rest.repository;

import com.docencia.rest.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
