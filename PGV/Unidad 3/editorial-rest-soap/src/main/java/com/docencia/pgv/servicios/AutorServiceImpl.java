package com.docencia.pgv.servicios;

import com.docencia.pgv.interfaces.AutorService;
import com.docencia.pgv.modelo.Autor;
import com.docencia.pgv.repositorios.interfaces.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImpl implements AutorService {
    private final AutorRepository autorRepository;

    /**
     * Constructor de la clase con el repositorio de autor
     *
     * @param autorRepository repositorio de autor
     */
    public AutorServiceImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    /**
     * Metodo para listar todos los autores
     *
     * @return lista de autores
     */
    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    /**
     * Metodo que devuelve el autor por el id de entrada o una excepcion si no encuentra ese id
     *
     * @param id identificador del autor
     * @return autor encontrado
     * @throws IllegalArgumentException si no existe el id
     */
    @Override
    public Autor findByIdOrThrow(Long id) {
        return autorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author id not found"));
    }

    /**
     * Metodo para crear un autor verificando que el nombre no esta vacio, en caso contrario
     * devuelve una excepcion
     *
     * @param autor entidad a guardar
     * @return autor guardado
     * @throws IllegalArgumentException si el nombre esta vacio
     */
    @Override
    public Autor create(Autor autor) {
        if (autor.getNombre().isEmpty() || autor.getNombre() == null)
            throw new IllegalArgumentException("Name can't be blank");
        autor.setId(null);
        return autorRepository.save(autor);
    }

    /**
     * Metodo para eliminar un autor por su id y arroja una excepcion si el id no existe
     *
     * @param id identificador del autor
     * @throws IllegalArgumentException si no existe el id de autor
     */
    @Override
    public void delete(Long id) {
        findByIdOrThrow(id);
        if (!autorRepository.deleteById(id)) throw new IllegalArgumentException("Author id not found");
    }
}
