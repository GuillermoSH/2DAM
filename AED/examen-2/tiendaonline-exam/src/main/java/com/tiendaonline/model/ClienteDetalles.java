package com.tiendaonline.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "cliente_detalles")
public class ClienteDetalles {
    @Id
    private String id;

    private int clienteId;

    private String telefono;

    private String notasInternas;

    public ClienteDetalles() {
    }

    public ClienteDetalles(String id) {
        this.id = id;
    }

    public ClienteDetalles(String id, int clienteId) {
        this.id = id;
        this.clienteId = clienteId;
    }

    public ClienteDetalles(String id, int clienteId, String telefono, String notasInternas) {
        this.id = id;
        this.clienteId = clienteId;
        this.telefono = telefono;
        this.notasInternas = notasInternas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNotasInternas() {
        return notasInternas;
    }

    public void setNotasInternas(String notasInternas) {
        this.notasInternas = notasInternas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClienteDetalles that = (ClienteDetalles) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ClienteDetalles{" +
                "id='" + id + '\'' +
                ", clienteId=" + clienteId +
                ", telefono='" + telefono + '\'' +
                ", notasInternas='" + notasInternas + '\'' +
                '}';
    }
}
