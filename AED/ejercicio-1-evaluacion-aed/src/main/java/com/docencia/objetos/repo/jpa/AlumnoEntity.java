package com.docencia.objetos.repo.jpa;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name="alumnos")
public class AlumnoEntity {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  
  @Column(unique = true)
  private String email;
  
  private String ciclo;

  @ManyToOne
  private RolEntity rol;

  public AlumnoEntity() {
  }

  public AlumnoEntity(Long id, String nombre, String email, String ciclo) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.ciclo = ciclo;
  }

  public AlumnoEntity(Long id, String nombre, String email, String ciclo, RolEntity rol) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.ciclo = ciclo;
    this.rol = rol;
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

  public RolEntity getRol() {
    return rol;
  }

  public void setRol(RolEntity rol) {
    this.rol = rol;
  }
}
