package com.docencia.rest.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "detalles")
    private DetalleProducto detalles;

    @Column(name = "categoria")
    private Categoria categoria;

    public Producto() {
    }

    public Producto(int id) {
        this.id = id;
    }

    public Producto(int id, String nombre, BigDecimal precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public DetalleProducto getDetalles() {
        return detalles;
    }

    public void setDetalles(DetalleProducto detalles) {
        this.detalles = detalles;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id && Objects.equals(nombre, producto.nombre) && Objects.equals(precio, producto.precio) && Objects.equals(stock, producto.stock) && Objects.equals(detalles, producto.detalles) && Objects.equals(categoria, producto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, precio, stock, detalles, categoria);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", detalles=" + detalles +
                ", categoria=" + categoria +
                '}';
    }
}
