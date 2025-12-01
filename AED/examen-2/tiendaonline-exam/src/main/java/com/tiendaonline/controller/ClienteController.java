package com.tiendaonline.controller;

import com.tiendaonline.model.Cliente;
import com.tiendaonline.model.Pedido;
import com.tiendaonline.service.interfaces.IClienteDetallesService;
import com.tiendaonline.service.interfaces.IClienteService;
import com.tiendaonline.service.interfaces.IPedidoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private IClienteService clienteService;
    private IClienteDetallesService clienteDetallesService;

    @Autowired
    public void setClienteService(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("")
    public List<Cliente> getAllSortedByNombre() {
        return clienteService.findAllSortedByNombre();
    }

    @PostMapping("")
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
        clienteDetallesService.saveDetallesForCliente(cliente.getId(), cliente.getDetalles());
        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = clienteService.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable("id") Integer id) {
        clienteService.deleteById(id);
    }
}
