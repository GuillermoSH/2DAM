package com.docencia.pgv.rest;

import com.docencia.pgv.interfaces.LibroService;
import com.docencia.pgv.modelo.Libro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@Tag(name = "Libros", description = "Operaciones sobre libros")
public class LibroRestController {
    private final LibroService libroService;

    @Autowired
    public LibroRestController(LibroService libroService) {
        this.libroService = libroService;
    }

    @Operation(summary = "Recoger todos los libros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los libros devueltos")
    })
    @GetMapping("")
    public ResponseEntity<List<Libro>> getAllBooks() {
        return ResponseEntity.ok(libroService.findAll());
    }

    @Operation(summary = "Recoger libro por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro devuelto"),
            @ApiResponse(responseCode = "404", description = "Id de libro no existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getBookById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(libroService.findByIdOrThrow(id));
        } catch (Exception ignored) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Recoger libros por id de autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libros devueltos"),
            @ApiResponse(responseCode = "404", description = "Id de autor no existe")
    })
    @GetMapping("/autor/{idAutor}")
    public ResponseEntity<List<Libro>> getBooksByAuthorId(@PathVariable("idAutor") Long idAutor) {
        try {
            return ResponseEntity.ok(libroService.findByAutorOrThrow(idAutor));
        } catch (Exception ignored) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Guardar un libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro guardado"),
            @ApiResponse(responseCode = "400", description = "La peticion no esta bien construida")
    })
    @PostMapping("")
    public ResponseEntity<Libro> createBook(@RequestBody Libro libro) {
        try {
            return ResponseEntity.ok(libroService.create(libro));
        } catch (Exception ignored) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Eliminar un libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Libro borrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Id de libro no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") Long id) {
        try {
            libroService.delete(id);
            return ResponseEntity.ok("Borrado correctamente");
        } catch (Exception ignored) {
            return ResponseEntity.notFound().build();
        }
    }
}
