package com.docencia.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class JpaAbstractRepository<T, ID> {

    protected final JpaRepository<T, ID> repository;

    protected JpaAbstractRepository(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
