package com.tiendaonline.service;

import com.tiendaonline.model.Cliente;
import com.tiendaonline.model.Pedido;
import com.tiendaonline.repository.ClienteRepository;
import com.tiendaonline.repository.PedidoRepository;
import com.tiendaonline.service.interfaces.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PedidoService implements IPedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Pedido crearPedido(Integer clienteId, String estado) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new IllegalArgumentException("No existe el cliente al que se quiere asignar el pedido"));
        return pedidoRepository.save(new Pedido(0, estado, cliente));
    }

    @Override
    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No existe el pedido que se esta buscando"));
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id no puede ser nulo");
        pedidoRepository.deleteById(id);
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }


}