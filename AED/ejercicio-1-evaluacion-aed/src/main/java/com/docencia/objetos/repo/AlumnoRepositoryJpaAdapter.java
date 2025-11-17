package com.docencia.objetos.repo;

import com.docencia.objetos.domain.Alumno;
import com.docencia.objetos.domain.Rol;
import com.docencia.objetos.mapper.AlumnoMapper;
import com.docencia.objetos.repo.interfaces.AlumnoRepository;
import com.docencia.objetos.repo.jpa.AlumnoEntity;
import com.docencia.objetos.repo.jpa.AlumnoJpaRepository;
import com.docencia.objetos.repo.jpa.RolEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("h2")
public class AlumnoRepositoryJpaAdapter implements AlumnoRepository {

  private final AlumnoJpaRepository jpa;

  public AlumnoRepositoryJpaAdapter(AlumnoJpaRepository jpa) {
    this.jpa = jpa;
  }

  @Override
  public List<Alumno> findAll() {
    return AlumnoMapper.to(jpa.findAll());
  }

  @Override
  public Optional<Alumno> findById(Long id) {
    if (jpa.findById(id).isEmpty()) return Optional.empty();
    return Optional.of(AlumnoMapper.to(jpa.findById(id).get()));
  }

  @Override
  public Alumno save(Alumno alumno) {
    return AlumnoMapper.to(jpa.save(AlumnoMapper.to(alumno)));
  }

  @Override
  public boolean existsByEmail(String email) {
    return jpa.existsByEmail(email);
  }

  @Override
  public void deleteById(Long id) {
    jpa.deleteById(id);
  }

  @Override
  public long count() {
    return jpa.count();
  }

}
