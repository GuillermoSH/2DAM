package com.docencia.com.proc_cli.repositories.file;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;

import org.springframework.stereotype.Repository;

import com.docencia.com.proc_cli.repositories.interfaces.JobRepository;

@Repository
public class FileJobRepository implements JobRepository {
    private String loggerFileName = "logger.txt";
    private Path loggerFilePath;

    public FileJobRepository() {
        try {
            this.loggerFilePath = getFilePath(loggerFileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Path getFilePath(String fileName) throws IOException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) throw new IOException("El fichero no exisite " + fileName);

        return Paths.get(resource.getPath());
    }

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
}
