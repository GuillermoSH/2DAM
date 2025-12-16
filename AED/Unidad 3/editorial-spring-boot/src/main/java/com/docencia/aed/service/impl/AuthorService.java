package com.docencia.aed.service.impl;

import com.docencia.aed.entity.Author;
import com.docencia.aed.entity.Book;
import com.docencia.aed.repository.AuthorRepository;
import com.docencia.aed.repository.BookRepository;
import com.docencia.aed.service.IAuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author id not found"));
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Book> findBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
