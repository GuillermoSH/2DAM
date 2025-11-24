package com.docencia.objetos.repo.json;

import com.docencia.objetos.domain.Rol;
import com.docencia.objetos.repo.interfaces.RolRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RolJsonFileRepository implements RolRepository {

    private final Path path;
    private final ObjectMapper mapper;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public RolJsonFileRepository(Path path, ObjectMapper mapper) {
        this.path = path;
        this.mapper = mapper;
    }

    @Override
    public List<Rol> findAll() {
        lock.readLock().lock();
        try {
            return Collections.unmodifiableList(readAllInternal());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Optional<Rol> findById(Long id) {
        lock.readLock().lock();
        try {
            for (Rol rol : readAllInternal()) {
                if (rol.getId().equals(id)) return Optional.of(rol);
            }
            return Optional.empty();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Optional<Rol> findByNombre(String nombre) {
        lock.readLock().lock();
        try {
            for (Rol rol : readAllInternal()) {
                if (rol.getNombre().equals(nombre)) return Optional.of(rol);
            }
            return Optional.empty();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Rol save(Rol rol) {
        lock.writeLock().lock();
        try {
            List<Rol> all = readAllInternal();
            if (rol.getId() == null) {
                rol.setId(UUID.randomUUID().getLeastSignificantBits());
            }
            all.removeIf(n -> Objects.equals(n.getId(), rol.getId()));
            all.add(rol);
            writeAllInternal(all);
            return rol;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void deleteById(Long id) {
        lock.writeLock().lock();
        try {
            List<Rol> all = readAllInternal();
            boolean removed = all.removeIf(n -> Objects.equals(n.getId(), id));
            if (removed) writeAllInternal(all);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public long count() {
        lock.readLock().lock();
        try {
            return findAll().size();
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Metodo para recoger toda la informacion del 'path' y lo devuelve en
     * el formato de la clase Rol.
     *
     * @return roles -> lista de roles encontrados
     * @throws RuntimeException si no puede leer el path
     */
    private List<Rol> readAllInternal() {
        try {
            if (!Files.exists(path) || Files.size(path) == 0) return new ArrayList<>();
            Rol[] roles = mapper.readValue(Files.readAllBytes(path), Rol[].class);
            return new ArrayList<>(Arrays.asList(roles));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    /**
     * Metodo para guardar todo el contenido del fichero de nuevo en
     * el 'path'.
     *
     * @param items toda la informacion que se quiere guardar
     */
    private void writeAllInternal(List<Rol> items) {
        try {
            byte[] bytes =
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(items);
            Files.write(path, bytes,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException("Error writing JSON", e);
        }
    }
}
