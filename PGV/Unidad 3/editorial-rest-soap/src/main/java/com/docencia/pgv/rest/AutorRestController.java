package com.docencia.pgv.rest;

import com.docencia.pgv.interfaces.AutorService;
import com.docencia.pgv.modelo.Autor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@Tag(name = "Autores", description = "Operaciones sobre autores")
public class AutorRestController {
    private final AutorService autorService;

    @Autowired
    public AutorRestController(AutorService autorService) {
        this.autorService = autorService;
    }

    @Operation(summary = "Recoger todos los autores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los autores devueltos")
    })
    @GetMapping("")
    public ResponseEntity<List<Autor>> getAllAuthors() {
        return ResponseEntity.ok(autorService.findAll());
    }

    @Operation(summary = "Recoger autor por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor devuelto"),
            @ApiResponse(responseCode = "404", description = "Id de autor no existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAuthorById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(autorService.findByIdOrThrow(id));
        } catch (Exception ignored) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Guardar un autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor guardado"),
            @ApiResponse(responseCode = "400", description = "La peticion no esta bien construida")
    })
    @PostMapping("")
    public ResponseEntity<Autor> createAuthor(@RequestBody Autor autor) {
        try {
            return ResponseEntity.ok(autorService.create(autor));
        } catch (Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Eliminar un autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor borrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Id de autor no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable("id") Long id) {
        try {
            autorService.delete(id);
            return ResponseEntity.ok("Borrado correctamente");
        } catch (Exception ignored) {
            return ResponseEntity.notFound().build();
        }
    }
}
