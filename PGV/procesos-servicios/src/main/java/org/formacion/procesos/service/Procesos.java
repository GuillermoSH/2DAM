package org.formacion.procesos.service;

import org.formacion.procesos.component.interfaces.IFicheroComponent;
import org.formacion.procesos.service.interfaces.IProcesos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Procesos implements IProcesos {
    IFicheroComponent componenteFichero;

    @Autowired
    public void setFicheroComponent(IFicheroComponent componenteFichero) {
        this.componenteFichero = componenteFichero;
    }

    public void ejecutar() {
        System.out.println("Ejecutando lógica del proceso...");
        String[] splittedCommand = "ls -l".split(" ");
        ProcessBuilder processbuilder = getProcessBuilder(splittedCommand);
        try {
            processbuilder.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private ProcessBuilder getProcessBuilder(String[] command) {
        return new ProcessBuilder(command);
    }
}