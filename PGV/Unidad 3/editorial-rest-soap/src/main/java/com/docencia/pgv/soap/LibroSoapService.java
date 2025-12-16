package com.docencia.pgv.soap;

import com.docencia.pgv.modelo.Libro;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "http://ies.docencia.com/libro", name = "LibroPortType")
public interface LibroSoapService {
    @WebMethod(operationName = "Listar los libros")
    List<Libro> getAllBooks();

    @WebMethod(operationName = "Buscar un libro por su id")
    Libro getBookById(@WebParam(name = "id") Long id);

    @WebMethod(operationName = "Buscar libros por su autor")
    List<Libro> getBooksByAuthor(@WebParam(name = "idAutor") Long idAutor);

    @WebMethod(operationName = "Crear un libro")
    Libro createBook(@WebParam(name = "titulo") String titulo,
                     @WebParam(name = "anioPublicacion") Integer anioPublicacion,
                     @WebParam(name = "idAutor") Long idAutor);

    @WebMethod(operationName = "Eliminar un libro")
    void deleteBook(@WebParam(name = "id") Long id);
}
