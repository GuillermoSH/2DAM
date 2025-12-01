package com.tiendaonline.controller;

import com.tiendaonline.model.Pedido;
import com.tiendaonline.service.PedidoService;
import com.tiendaonline.service.interfaces.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
    private IPedidoService pedidoService;

    @Autowired
    public void setPedidoService(IPedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("")
    public List<Pedido> getAllPedidos() {
        return pedidoService.findAll();
    }

    @PostMapping("/{clientId}/{status}")
    public Pedido savePedido(@PathVariable("status") String estado, @PathVariable("clientId") Integer clienteId) {
        return pedidoService.crearPedido(clienteId, estado);
    }

    @GetMapping("/{id}")
    public Pedido getPedido(@PathVariable("id") Integer id) {
        return pedidoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable("id") Integer id) {
        pedidoService.deleteById(id);
    }
}
