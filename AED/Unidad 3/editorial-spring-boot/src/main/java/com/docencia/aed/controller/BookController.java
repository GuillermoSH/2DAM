package com.docencia.aed.controller;

import com.docencia.aed.entity.Author;
import com.docencia.aed.entity.Book;
import com.docencia.aed.service.IBookService;
import com.docencia.aed.service.impl.AuthorService;
import com.docencia.aed.service.impl.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Books", description = "Operaciones sobre libros")
public class BookController {
    private IBookService bookService;

    @Autowired
    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books")
    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @Operation(summary = "Get book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtained book"),
            @ApiResponse(responseCode = "404", description = "Book id not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(bookService.findById(id));
        } catch (Exception ignored) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Save book associated with an author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book saved"),
            @ApiResponse(responseCode = "500", description = "Error occurred while saving the book")
    })
    @PostMapping("?authorId={authorId}")
    public ResponseEntity<Book> createBook(@RequestBody Book book, @PathVariable("authorId") Long authorId) {
        try {
            return ResponseEntity.ok(bookService.createByAuthor(book, authorId));
        } catch(Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
