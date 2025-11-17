package com.docencia.objetos.mapper;

import com.docencia.objetos.domain.Alumno;
import com.docencia.objetos.repo.jpa.AlumnoEntity;

import java.util.ArrayList;
import java.util.List;

public class AlumnoMapper {
    public static Alumno to(AlumnoEntity entity) {
        Alumno alumno = new Alumno(entity.getId(), entity.getNombre(), entity.getEmail(), entity.getCiclo());
        if (entity.getRol() != null) {
            alumno.setRol(RolMapper.to(entity.getRol()));
        }
        return alumno;
    }

    public static AlumnoEntity to(Alumno alumno) {
        AlumnoEntity entity = new AlumnoEntity(alumno.getId(), alumno.getNombre(), alumno.getEmail(), alumno.getCiclo());
        if (alumno.getRol() != null) {
            entity.setRol(RolMapper.to(alumno.getRol()));
        }
        return entity;
    }

    public static List<Alumno> to(List<AlumnoEntity> entities) {
        List<Alumno> alumnos = new ArrayList<>();
        for (AlumnoEntity entity : entities) {
            alumnos.add(to(entity));
        }
        return alumnos;
    }
}
