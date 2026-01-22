package com.tiendaonline.service;

import com.tiendaonline.model.Cliente;
import com.tiendaonline.model.ClienteDetalles;
import com.tiendaonline.repository.ClienteDetallesRepository;
import com.tiendaonline.repository.ClienteRepository;
import com.tiendaonline.service.interfaces.IClienteDetallesService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteDetallesService implements IClienteDetallesService {

    private final ClienteDetallesRepository clienteDetallesRepository;
    private final ClienteRepository clienteRepository;

    public ClienteDetallesService(ClienteDetallesRepository clienteDetallesRepository, ClienteRepository clienteRepository) {
        this.clienteDetallesRepository = clienteDetallesRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<ClienteDetalles> findByClienteId(Integer clienteId) {
        if (clienteId == null) throw new IllegalArgumentException("Id de cliente no puede ser nulo");
        Optional<ClienteDetalles> clienteDetalles = clienteDetallesRepository.findByClienteId(clienteId);
        return clienteDetalles.isPresent() ? clienteDetalles : Optional.empty();
    }

    @Override
    public ClienteDetalles saveDetallesForCliente(Integer clienteId, ClienteDetalles detalles) {
        if (clienteId == null || detalles == null)
            throw new IllegalArgumentException("Id de cliente y sus detalles no pueden ser nulos");
        ClienteDetalles clienteDetalles = clienteDetallesRepository.save(detalles);
        clienteRepository.save(new Cliente(clienteId, clienteDetalles));
        return clienteDetalles;
    }
}
