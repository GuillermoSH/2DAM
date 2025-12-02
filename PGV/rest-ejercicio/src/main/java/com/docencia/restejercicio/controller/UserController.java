package com.docencia.restejercicio.controller;

import com.docencia.restejercicio.model.Task;
import com.docencia.restejercicio.model.User;
import com.docencia.restejercicio.service.TaskService;
import com.docencia.restejercicio.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "Usuarios", description = "Operaciones sobre usuarios")
public class UserController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Recoger todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting users list"),
            @ApiResponse(responseCode = "204", description = "No content found")
    })
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = service.getAll();
        if (users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Recoger el usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch(Exception ignored) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "500", description = "User couldn't be deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        boolean isDeleted = service.delete(id);
        if (!isDeleted) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("Deleted successfully");
    }

    @Operation(summary = "Insert user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User inserted successfully"),
            @ApiResponse(responseCode = "500", description = "User couldn't be inserted")
    })
    @PostMapping("")
    public ResponseEntity<User> createTask(@RequestBody User user) {
        try {
            return ResponseEntity.ok(service.create(user));
        } catch (Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "500", description = "User couldn't be updated")
    })
    @PutMapping("/{id}")
    public ResponseEntity<User> updateTask(@RequestBody User user, @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.update(id, user));
        } catch (Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
