package com.docencia.service;

import com.docencia.model.Note;
import com.docencia.repo.INoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class NoteService extends ServiceNoteAbstract {

    private final INoteRepository repo;

    public NoteService(INoteRepository repository) {
        this.repo = repository;
    }

    @Override
    public List<Note> findAll() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public Note save(Note n) {
        return repo.save(n);
    }

    @Override
    @Transactional(readOnly = true)
    public Note findById(String id) {
        return repo.findById(id);
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        return repo.delete(id);
    }

    @Override
    public boolean exists(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exists'");
    }

    @Override
    public String noteToString(Note note) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'noteToString'");
    }

    @Override
    public Note stringToNote(String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stringToNote'");
    }
}