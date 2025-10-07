package org.formacion.procesos.component;

import org.formacion.procesos.repository.IFicheroReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FicheroComponent implements IFicheroComponent {
    @Autowired
    IFicheroReposiroty repositoryFichero;

    @Override
    public String mensaje() {
        return repositoryFichero.saludar();
    }

}
