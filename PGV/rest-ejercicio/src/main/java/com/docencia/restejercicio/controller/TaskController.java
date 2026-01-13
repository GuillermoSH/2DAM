package com.docencia.restejercicio.controller;

import com.docencia.restejercicio.common.ApiRestController;
import com.docencia.restejercicio.model.Task;
import com.docencia.restejercicio.service.TaskService;
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
@RequestMapping("/api/tasks")
@Tag(name = "Tareas", description = "Operaciones sobre tareas")
public class TaskController {
    private TaskService service;

    @Autowired
    public void setService(TaskService service) {
        this.service = service;
    }

    @Operation(summary = "Recoger todas las tareas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting tasks list"),
            @ApiResponse(responseCode = "204", description = "No content found")
    })
    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = service.getAll();
        if (tasks.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Recoger la tarea por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting task"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch(Exception ignored) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Task couldn't be deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable("id") Long id) {
        boolean isDeleted = service.delete(id);
        if (!isDeleted) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("Deleted successfully");
    }

    @Operation(summary = "Insert task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task inserted successfully"),
            @ApiResponse(responseCode = "500", description = "Task couldn't be inserted")
    })
    @PostMapping("")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try {
            return ResponseEntity.ok(service.create(task));
        } catch (Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "500", description = "Task couldn't be updated")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.update(id, task));
        } catch (Exception ignored) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
