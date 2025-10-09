package org.formacion.procesos.repository;

import org.springframework.stereotype.Repository;
import org.formacion.procesos.repository.interfaces.*;

@Repository
public class FicheroRepository implements IAlmacenamientoRepository {

    @Override
    public String saludar() {
        return "Te estoy saludando desde el repositorio de fichero";
    }

}
