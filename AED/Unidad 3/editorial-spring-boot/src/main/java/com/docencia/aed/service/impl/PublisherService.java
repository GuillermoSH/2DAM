package com.docencia.aed.service.impl;

import com.docencia.aed.entity.Publisher;
import com.docencia.aed.repository.PublisherRepository;
import com.docencia.aed.service.IPublisherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService implements IPublisherService {
    private final PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Publisher> findAll() {
        return repository.findAll();
    }

    @Override
    public Publisher create(Publisher publisher) {
        return repository.save(publisher);
    }
}
