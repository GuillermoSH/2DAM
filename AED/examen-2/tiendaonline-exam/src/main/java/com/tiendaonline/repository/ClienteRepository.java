package com.tiendaonline.repository;

import com.tiendaonline.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository  extends JpaRepository<Cliente, Integer> {
    List<Cliente> findAllSortedByNombreIgnoreCase();
}
