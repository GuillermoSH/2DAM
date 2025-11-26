package com.docencia.rest.controller;

import com.docencia.rest.model.Producto;
import com.docencia.rest.service.ProductoServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductosController {
    private ProductoServiceInterface productoService;

    @Autowired
    public void setProductoService(ProductoServiceInterface productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Get all productos")
    @GetMapping("")
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @Operation(summary = "Get productos by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Producto not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getUserById(@PathVariable(value = "id") Integer productoId) {
        Producto producto = productoService.findById(productoId).orElse(null);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(producto);
    }

    @Operation(summary = "Insert producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("")
    public Producto createProducto(@Valid @RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @Operation(summary = "Update producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto updated successfully"),
            @ApiResponse(responseCode = "404", description = "Producto not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable(value = "id") int productoId, @Valid @RequestBody Producto producto) {
        final Producto updatedProducto = productoService.save(producto);
        return ResponseEntity.ok(updatedProducto);
    }

    @Operation(summary = "Delete producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Producto not found")
    })
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteProducto(@PathVariable(value = "id") int productoId) {
        productoService.deleteById(productoId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
