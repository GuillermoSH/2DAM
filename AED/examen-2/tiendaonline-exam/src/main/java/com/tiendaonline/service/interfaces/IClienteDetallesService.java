package com.tiendaonline.service.interfaces;

import com.tiendaonline.model.ClienteDetalles;

import java.util.Optional;

public interface IClienteDetallesService {
    /**
     * Metodo para encontrar detalles por el id del cliente
     * @param clienteId id del cliente
     * @return detalles de cliente
     */
    Optional<ClienteDetalles> findByClienteId(Integer clienteId);

    /**
     * Metodo para guardar los detalles del cliente por su id
     * @param clienteId id del cliente
     * @param detalles detalles a guardar
     * @return clase detalles del cliente
     */
    ClienteDetalles saveDetallesForCliente(Integer clienteId, ClienteDetalles detalles);
}
