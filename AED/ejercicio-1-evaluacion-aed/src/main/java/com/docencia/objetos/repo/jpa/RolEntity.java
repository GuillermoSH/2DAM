package com.docencia.objetos.repo.jpa;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class RolEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String nombre;

  @OneToMany
  @JoinColumn(name = "alumnos")
  private List<AlumnoEntity> alumnos = new ArrayList<>();

  public RolEntity() {
  }

  public RolEntity(Long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
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

  public List<AlumnoEntity> getAlumnos() {
    return alumnos;
  }

  public void setAlumnos(List<AlumnoEntity> alumnos) {
    this.alumnos = alumnos;
  }
}
