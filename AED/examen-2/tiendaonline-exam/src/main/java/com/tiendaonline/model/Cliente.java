package com.tiendaonline.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private String email;
    @OneToMany
    private List<Pedido> pedidos;
    private ClienteDetalles detalles;

    public Cliente() {
    }

    public Cliente(int id, ClienteDetalles detales) {
        this.id = id;
        this.detalles = detales;
    }

    public Cliente(int id, String nombre, String email, List<Pedido> pedidos, ClienteDetalles detalles) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.pedidos = pedidos;
        this.detalles = detalles;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ClienteDetalles getDetalles() {
        return detalles;
    }

    public void setDetalles(ClienteDetalles detalles) {
        this.detalles = detalles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", pedidos=" + pedidos +
                ", detalles=" + detalles +
                '}';
    }
}
