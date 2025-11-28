package com.docencia.rest.repository;

import com.docencia.rest.model.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {

}
