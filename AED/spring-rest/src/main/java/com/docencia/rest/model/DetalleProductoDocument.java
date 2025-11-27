package com.docencia.rest.model;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Document(collection = "producto_detalle")
public class DetalleProductoDocument {
    @Id
    private int id;
    private int productoId;
    private String descripcionLarga;
    private Map<String, String> especificacionesTecnicas;
    private List<String> tags;

    public DetalleProductoDocument() {
    }

    public DetalleProductoDocument(int id) {
        this.id = id;
    }

    public DetalleProductoDocument(int id, int productoId, String descripcionLarga, Map<String, String> especificacionesTecnicas, List<String> tags) {
        this.id = id;
        this.productoId = productoId;
        this.descripcionLarga = descripcionLarga;
        this.especificacionesTecnicas = especificacionesTecnicas;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public Map<String, String> getEspecificacionesTecnicas() {
        return especificacionesTecnicas;
    }

    public void setEspecificacionesTecnicas(Map<String, String> especificacionesTecnicas) {
        this.especificacionesTecnicas = especificacionesTecnicas;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DetalleProductoDocument that = (DetalleProductoDocument) o;
        return Objects.equals(id, that.id) && Objects.equals(productoId, that.productoId) && Objects.equals(descripcionLarga, that.descripcionLarga) && Objects.equals(especificacionesTecnicas, that.especificacionesTecnicas) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productoId, descripcionLarga, especificacionesTecnicas, tags);
    }

    @Override
    public String toString() {
        return "DetalleProducto{" +
                "id='" + id + '\'' +
                ", productoId=" + productoId +
                ", descripcionLarga='" + descripcionLarga + '\'' +
                ", especificacionesTecnicas=" + especificacionesTecnicas +
                ", tags=" + tags +
                '}';
    }
}
