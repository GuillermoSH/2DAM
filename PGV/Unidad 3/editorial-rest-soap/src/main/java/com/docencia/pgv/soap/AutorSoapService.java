package com.docencia.pgv.soap;

import com.docencia.pgv.modelo.Autor;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "http://ies.docencia.com/autor", name = "AutorPortType")
public interface AutorSoapService {
    @WebMethod(operationName = "Listar autores")
    List<Autor> getAllAuthors();

    @WebMethod(operationName = "Buscar un autor por su id")
    Autor getAuthorById(@WebParam(name = "id") Long id);

    @WebMethod(operationName = "Crear un autor")
    Autor createAuthor(@WebParam(name = "nombre") String nombre, @WebParam(name = "pais") String pais);

    @WebMethod(operationName = "Eliminar un autor")
    void deleteAuthor(@WebParam(name = "id") Long id);
}
