package com.docencia.aed.service;

import com.docencia.aed.entity.Publisher;

import java.util.List;

public interface IPublisherService {
    List<Publisher> findAll();
    Publisher create(Publisher publisher);
}
