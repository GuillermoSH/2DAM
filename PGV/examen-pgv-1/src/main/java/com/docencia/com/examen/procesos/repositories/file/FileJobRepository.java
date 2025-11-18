package com.docencia.com.examen.procesos.repositories.file;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;

import org.springframework.stereotype.Repository;

import com.docencia.com.examen.procesos.repositories.interfaces.JobRepository;

@Repository
public class FileJobRepository implements JobRepository {
    private String loggerFileName = "logger.txt";
    private Path loggerFilePath;

    public FileJobRepository() {
        try {
            this.loggerFilePath = getFilePath(this.loggerFileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public FileJobRepository(String loggerFileName) {
        try {
            this.loggerFilePath = getFilePath(loggerFileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo para agregar contenido al archivo verificando que se ha podido ejecutar
     * el write o no
     * @param content contenido a agregar
     * @return flag si se pudo ejecutar
     */
    @Override
    public boolean add(String content) {
        boolean isMounted = true;

        try {
            Files.write(this.loggerFilePath, content.getBytes(), StandardOpenOption.APPEND);
            return isMounted;
        } catch (Exception e) {
            return !isMounted;
        }
    }

    /**
     * Metodo que verifica que se puede obtener el archivo que se esta buscando
     * @param fileName nombre del archivo a buscar
     * @return el Path del archivo localizado
     * @throws IOException si no se encuentra el archivo
     */
    private Path getFilePath(String fileName) throws IOException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) throw new IOException("El fichero no existe " + fileName);

        return Paths.get(resource.getPath());
    }
}
