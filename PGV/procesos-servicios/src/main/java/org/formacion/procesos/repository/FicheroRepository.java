package org.formacion.procesos.repository;

import org.springframework.stereotype.Repository;

@Repository
public class FicheroRepository implements IFicheroReposiroty {

    @Override
    public String saludar() {
        return "Te estoy saludando desde el repositorio de fichero";
    }

}
