<<<<<<< HEAD
package com.docencia.tareas.mapper;

import com.docencia.tareas.model.Alumno;
import com.docencia.tareas.model.Tarea;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-22T15:19:20+0000",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260101-2150, environment: Java 21.0.9 (Eclipse Adoptium)"
)
public class TareaMapperImpl implements TareaMapper {

    @Override
    public Alumno toAlumno(Tarea tarea) {
        if ( tarea == null ) {
            return null;
        }

        Alumno alumno = new Alumno();

        alumno.setIdentificador( tarea.getId() );
        alumno.setNombre( tarea.getTitulo() );

        return alumno;
    }

    @Override
    public List<Alumno> toAlumnos(List<Tarea> tareas) {
        if ( tareas == null ) {
            return null;
        }

        List<Alumno> list = new ArrayList<Alumno>( tareas.size() );
        for ( Tarea tarea : tareas ) {
            list.add( toAlumno( tarea ) );
        }

        return list;
    }
}
=======
package com.docencia.tareas.mapper;

import com.docencia.tareas.model.Alumno;
import com.docencia.tareas.model.Tarea;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-21T14:42:43+0000",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260101-2150, environment: Java 21.0.9 (Eclipse Adoptium)"
)
public class TareaMapperImpl implements TareaMapper {

    @Override
    public Alumno toAlumno(Tarea tarea) {
        if ( tarea == null ) {
            return null;
        }

        Alumno alumno = new Alumno();

        alumno.setIdentificador( tarea.getId() );
        alumno.setNombre( tarea.getTitulo() );

        return alumno;
    }

    @Override
    public List<Alumno> toAlumnos(List<Tarea> tareas) {
        if ( tareas == null ) {
            return null;
        }

        List<Alumno> list = new ArrayList<Alumno>( tareas.size() );
        for ( Tarea tarea : tareas ) {
            list.add( toAlumno( tarea ) );
        }

        return list;
    }
}
>>>>>>> b5aaef97f12fe63a31c3cc348b549c9f1a9bff98
