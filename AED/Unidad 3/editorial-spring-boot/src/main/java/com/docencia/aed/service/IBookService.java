package com.docencia.aed.service;

import com.docencia.aed.entity.Author;
import com.docencia.aed.entity.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    Book findById(Long id);
    Book createByAuthor(Book book, Long authorId);
}
