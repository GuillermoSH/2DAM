package org.formacion.procesos.service;

import org.formacion.procesos.component.IFicheroComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Procesos {
    IFicheroComponent componenteFichero;

    @Autowired
    public void setFicheroComponent(IFicheroComponent componenteFichero) {
        this.componenteFichero = componenteFichero;
    }

    public void ejecutar() {
        System.out.println("Ejecutando lógica del proceso...");
        System.out.println(componenteFichero.mensaje());
    }
    
    public ProcessBuilder getProcessBuilder(String[] command) {
        if (command.length == 1) return new ProcessBuilder(command[0]);
        if (command.length == 2) return new ProcessBuilder(command[0], command[1]);
        return new ProcessBuilder(command[0], command[1], command[2]);
    }
}