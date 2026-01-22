package com.docencia.pgv.soap;

import com.docencia.pgv.interfaces.AutorService;
import com.docencia.pgv.modelo.Autor;
import jakarta.jws.WebService;
import org.springframework.stereotype.Service;

import java.util.List;

@WebService(serviceName = "AutorService", portName = "AutorPort", targetNamespace = "http://ies.docencia.com/autor", endpointInterface = "com.docencia.pgv.soap.AutorSoapService")
@Service
public class AutorSoapServiceImpl implements AutorSoapService {
    private AutorService autorService;

    public AutorSoapServiceImpl(AutorService autorService) {
        this.autorService = autorService;
    }

    @Override
    public List<Autor> getAllAuthors() {
        return autorService.findAll();
    }

    @Override
    public Autor getAuthorById(Long id) {
        return autorService.findByIdOrThrow(id);
    }

    @Override
    public Autor createAuthor(String nombre, String pais) {
        return autorService.create(new Autor(null, nombre, pais));
    }

    @Override
    public void deleteAuthor(Long id) {
        autorService.delete(id);
    }
}
