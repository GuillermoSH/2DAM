package com.docencia.objetos.domain;

import java.util.Objects;

public class Alumno {
  private Long id;
  private String nombre;
  private String email;
  private String ciclo;

  public Alumno() {
  }

  public Alumno(Long id) {
    this.id = id;
  }

  public Alumno(Long id, String nombre, String email, String ciclo) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.ciclo = ciclo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public String getCiclo() {
    return ciclo;
  }

  public void setCiclo(String ciclo) {
    this.ciclo = ciclo;
  }

  @Override
  public String toString() {
    return "Alumno{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", email='" + email + '\'' +
            ", ciclo='" + ciclo + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Alumno alumno = (Alumno) o;
    if (id == null || alumno.getId() == null) return false;
    return Objects.equals(id, alumno.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
