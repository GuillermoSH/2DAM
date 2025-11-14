package com.docencia.objetos.repo.jpa;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "alumnos")
public class AlumnoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "ciclo")
  private String ciclo;

  public AlumnoEntity() {
  }

  public AlumnoEntity(Long id) {
    this.id = id;
  }

  public AlumnoEntity(Long id, String nombre, String email, String ciclo) {
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
    AlumnoEntity alumno = (AlumnoEntity) o;
    if (id == null || alumno.getId() == null) return false;
    return Objects.equals(id, alumno.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
