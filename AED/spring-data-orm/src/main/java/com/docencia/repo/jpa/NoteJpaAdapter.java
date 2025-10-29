package com.docencia.repo.jpa;

import com.docencia.repo.INoteRepository;
import com.docencia.model.Note;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Profile("sqlite")
public class NoteJpaAdapter implements INoteRepository {

    private final INoteJpaRepository repository;

    public NoteJpaAdapter(INoteJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public Note findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Note find(Note example) {
        return repository.findFirstByTitle(example.getTitle()).orElse(null);
    }

    @Override
    public List<Note> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Note save(Note note) {
        if (note.getId() == null) {
            note.setId(UUID.randomUUID().toString());
        }
        return repository.save(note);
    }

  @Override @Transactional
  public boolean delete(String id){
    if(!repository.existsById(id)) {
      return false;
    } 
    repository.deleteById(id);
    return true;
  }
}