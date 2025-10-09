package org.formacion.procesos.component;

import org.formacion.procesos.component.interfaces.IFicheroComponent;
import org.formacion.procesos.repository.interfaces.IAlmacenamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FicheroComponent implements IFicheroComponent {
    @Autowired
    IAlmacenamientoRepository repositoryFichero;

    @Override
    public String mensaje() {
        return repositoryFichero.saludar();
    }

    /**
     * Crear enum para los estados de los comandos OK/ERROR
     */
}
