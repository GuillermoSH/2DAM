package com.docencia.pgv.servicios;

import com.docencia.pgv.interfaces.LibroService;
import com.docencia.pgv.modelo.Libro;
import com.docencia.pgv.repositorios.interfaces.AutorRepository;
import com.docencia.pgv.repositorios.interfaces.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    /**
     * Constructor de la clase con el repositorio de autor y de libro
     *
     * @param autorRepository repositorio de autor
     * @param libroRepository repositorio de libro
     */
    public LibroServiceImpl(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    /**
     * Metodo para listar todos los libros
     *
     * @return lista de libros
     */
    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    /**
     * Metodo que devuelve el libro por el id de entrada o una excepcion si no encuentra ese id
     *
     * @param id identificador del libro
     * @return libro encontrado
     * @throws IllegalArgumentException si no existe el id
     */
    @Override
    public Libro findByIdOrThrow(Long id) {
        return libroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book id not found"));
    }

    /**
     * Metodo que devuelve la lista de libros por el id de autor o una excepcion si no
     * encuentra ese id
     *
     * @param idAutor identificador del autor
     * @return lista de libros
     * @throws IllegalArgumentException si no existe el id
     */
    @Override
    public List<Libro> findByAutorOrThrow(Long idAutor) {
        if (authorNotExistsById(idAutor)) throw new IllegalArgumentException("Author id not found");
        return libroRepository.findByIdAutor(idAutor);
    }

    /**
     * Metodo para crear un libro verificando que el titulo no esta vacio y que el autor existe,
     * en caso contrario devuelve una excepcion
     *
     * @param libro entidad a guardar
     * @return libro guardado
     * @throws IllegalArgumentException si el titulo esta vacio o no existe el autor
     */
    @Override
    public Libro create(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().isEmpty())
            throw new IllegalArgumentException("Title can't be blank");
        if (authorNotExistsById(libro.getIdAutor())) throw new IllegalArgumentException("Author id must exists");
        return libroRepository.save(libro);
    }

    /**
     * Metodo para eliminar un libro por su id y arroja una excepcion si el id no existe
     *
     * @param id identificador del libro
     * @throws IllegalArgumentException si no existe el id de libro
     */
    @Override
    public void delete(Long id) {
        findByIdOrThrow(id);
        libroRepository.deleteById(id);
    }

    /**
     * Metodo para devolver si el autor existe o no (en este caso esta invertido
     * porque solo se evalua que no existe en el codigo)
     *
     * @param authorId id del autor
     * @return true si no existe el id en la bbdd
     */
    private boolean authorNotExistsById(Long authorId) {
        return autorRepository.findById(authorId).isEmpty();
    }
}
