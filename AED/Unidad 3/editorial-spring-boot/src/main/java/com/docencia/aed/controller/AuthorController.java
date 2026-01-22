package com.docencia.aed.controller;

import com.docencia.aed.entity.Author;
import com.docencia.aed.entity.Book;
import com.docencia.aed.service.IAuthorService;
import com.docencia.aed.service.impl.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@Tag(name = "Authors", description = "Operaciones sobre autores")
public class AuthorController {
    private IAuthorService authorService;

    @Autowired
    public void setAuthorService(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(summary = "Get all authors")
    @GetMapping("")
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @Operation(summary = "Get author by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtained author"),
            @ApiResponse(responseCode = "404", description = "Author id not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(authorService.findById(id));
        } catch (Exception ignored) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Save author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author saved"),
            @ApiResponse(responseCode = "404", description = "Error occurred while saving the author")
    })
    @PostMapping("")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        try {
            return ResponseEntity.ok(authorService.create(author));
        } catch(Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get books by author id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtained list of books"),
            @ApiResponse(responseCode = "404", description = "Author id not found")
    })
    @GetMapping("/{id}/books")
    public ResponseEntity<List<Book>> getAllAuthorBooks(@PathVariable("id") Long authorId) {
        try {
            return ResponseEntity.ok(authorService.findBooksByAuthor(authorId));
        } catch (Exception ignored) {
            return ResponseEntity.notFound().build();
        }
    }
}
