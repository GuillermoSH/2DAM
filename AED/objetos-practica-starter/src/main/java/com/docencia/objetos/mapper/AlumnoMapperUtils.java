package com.docencia.objetos.mapper;

import com.docencia.objetos.domain.Alumno;
import com.docencia.objetos.repo.jpa.AlumnoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlumnoMapperUtils {
    public static Alumno to(AlumnoEntity alumnoEntity) {
        if (alumnoEntity == null) return null;
        return new Alumno(alumnoEntity.getId(), alumnoEntity.getNombre(), alumnoEntity.getEmail(), alumnoEntity.getCiclo());
    }

    public static AlumnoEntity to(Alumno alumno) {
        if (alumno == null) return null;
        return new AlumnoEntity(alumno.getId(), alumno.getNombre(), alumno.getEmail(), alumno.getCiclo());
    }

    public static Optional<Alumno> to(Optional<AlumnoEntity> alumnoEntity) {
        return alumnoEntity.flatMap(entity -> to(alumnoEntity));
    }

    public static List<Alumno> to(List<AlumnoEntity> alumnoEntityList) {
        if (alumnoEntityList.isEmpty()) return new ArrayList<>();
        List<Alumno> alumnos = new ArrayList<>();
        for (AlumnoEntity alumno : alumnoEntityList) {
            alumnos.add(to(alumno));
        }
        return alumnos;
    }

//    public static List<AlumnoEntity> to(List<Alumno> alumnos) {
//        if (alumnos.isEmpty()) return new ArrayList<>();
//        List<AlumnoEntity> alumnoEntityList = new ArrayList<>();
//        for (Alumno alumno : alumnos) {
//            alumnoEntityList.add(to(alumno));
//        }
//        return alumnoEntityList;
//    }
}
