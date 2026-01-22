package com.tiendaonline.service;

import com.tiendaonline.model.Cliente;
import com.tiendaonline.repository.ClienteRepository;
import com.tiendaonline.service.interfaces.IClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService{

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAllSortedByNombre() {
        return clienteRepository.findAllSortedByNombreIgnoreCase();
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id no puede ser nulo");
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return (cliente.isPresent()) ? cliente : Optional.empty();
    }

    @Override
    public Cliente save(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("Cliente no puede ser nulo");
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id no puede ser nulo");
        clienteRepository.deleteById(id);
    }


}
