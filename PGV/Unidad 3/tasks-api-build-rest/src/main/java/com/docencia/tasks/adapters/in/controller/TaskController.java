package com.docencia.tasks.adapters.in.controller;

import com.docencia.tasks.adapters.in.api.TaskRequest;
import com.docencia.tasks.adapters.in.api.TaskResponse;
import com.docencia.tasks.adapters.mapper.TaskMapper;
import com.docencia.tasks.business.ITaskService;
import com.docencia.tasks.domain.model.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.docencia.tasks.entitys.TaskEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Tasks API")
@CrossOrigin
public class TaskController {
    private final ITaskService taskService;
    private TaskMapper mapper;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setMapper(TaskMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get all tasks")
    public List<TaskResponse> getAll() {
        return mapper.toResponses(taskService.getTasks());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by id")
    public TaskResponse getAll(@PathVariable Long id) {
        return mapper.toResponse(taskService.getTaskById(id));
    }

    @PostMapping
    @Operation(summary = "Create new task")
    public TaskResponse create(@RequestBody TaskRequest taskRequest) {
        Task task = mapper.toTask(taskRequest);
        return mapper.toResponse(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update task")
    public TaskResponse update(@PathVariable Long id, @RequestBody Task task) {
        task.setCompleted(task.isCompleted());
        task.setTitle(task.getTitle());
        task.setDescription(task.getDescription());
        return mapper.toResponse(taskService.updateTask(task));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task")
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(new Task(id));
    }
}
