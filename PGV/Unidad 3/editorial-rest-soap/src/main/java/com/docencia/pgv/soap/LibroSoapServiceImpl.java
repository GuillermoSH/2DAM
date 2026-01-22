package com.docencia.pgv.soap;

import com.docencia.pgv.interfaces.LibroService;
import com.docencia.pgv.modelo.Libro;
import jakarta.jws.WebService;
import org.springframework.stereotype.Service;

import java.util.List;

@WebService(serviceName = "LibroService", portName = "LibroPort", targetNamespace = "http://ies.docencia.com/libro", endpointInterface = "com.docencia.pgv.soap.LibroSoapService")
@Service
public class LibroSoapServiceImpl implements LibroSoapService {
    private LibroService libroService;

    public LibroSoapServiceImpl(LibroService libroService) {
        this.libroService = libroService;
    }

    @Override
    public List<Libro> getAllBooks() {
        return libroService.findAll();
    }

    @Override
    public Libro getBookById(Long id) {
        return libroService.findByIdOrThrow(id);
    }

    @Override
    public List<Libro> getBooksByAuthor(Long idAutor) {
        return libroService.findByAutorOrThrow(idAutor);
    }

    @Override
    public Libro createBook(String titulo, Integer anioPublicacion, Long idAutor) {
        return libroService.create(new Libro(null, titulo, anioPublicacion, idAutor));
    }

    @Override
    public void deleteBook(Long id) {
        libroService.delete(id);
    }
}
