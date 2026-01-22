package com.tiendaonline.repository;

import com.tiendaonline.model.ClienteDetalles;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClienteDetallesRepository extends MongoRepository<ClienteDetalles, Integer> {
    Optional<ClienteDetalles> findByClienteId(int clienteId);

    boolean deleteByClienteId(int clienteId);
}
