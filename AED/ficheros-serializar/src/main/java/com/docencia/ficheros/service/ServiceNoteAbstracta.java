package com.docencia.ficheros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.docencia.ficheros.model.Note;
import com.docencia.ficheros.repo.INotRepository;

public abstract class ServiceNoteAbstracta implements IServiceNote{

    @Autowired
    INotRepository notRepository;


    public INotRepository getNotRepository() {
        return this.notRepository;
    }

    public void setNotRepository(INotRepository notRepository) {
        this.notRepository = notRepository;
    }

    @Override
    public List<Note> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}