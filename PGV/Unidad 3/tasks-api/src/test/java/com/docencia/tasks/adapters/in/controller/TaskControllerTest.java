package com.docencia.tasks.adapters.in.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.docencia.tasks.adapters.in.api.TaskRequest;
import com.docencia.tasks.adapters.in.api.TaskResponse;
import com.docencia.tasks.adapters.in.controller.TaskController;
import com.docencia.tasks.adapters.mapper.TaskMapper;
import com.docencia.tasks.business.ITaskService;
import com.docencia.tasks.domain.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {
  Task taskWithId = null;
  Task taskWithoutId = null;

  @BeforeEach
  void setUp() {
    taskWithId = new Task(1L, "a", "b", false);
    taskWithoutId = new Task(null, "a", "b", false);
  }

  @Test
  void getAll_returnsMappedResponses() {
    ITaskService service = mock(ITaskService.class);
    TaskMapper mapper = mock(TaskMapper.class);
    TaskController controller = new TaskController(service, mapper);

    when(service.getAll()).thenReturn(List.of(taskWithId));
    when(mapper.toResponse(taskWithId)).thenReturn(new TaskResponse(1L, "a", "b", false));

    List<TaskResponse> res = controller.getAll();

    assertEquals(1, res.size());
    assertEquals(1L, res.get(0).getId());
    verify(service).getAll();
    verify(mapper).toResponse(taskWithId);
  }

  @Test
  void getById_returns404_whenNotFound() {
    ITaskService service = mock(ITaskService.class);
    TaskMapper mapper = mock(TaskMapper.class);
    TaskController controller = new TaskController(service, mapper);

    when(service.getById(10L)).thenReturn(Optional.empty());

    var resp = controller.getById(10L);

    assertEquals(404, resp.getStatusCode().value());
  }

  @Test
  void create_returns201_andBody() {
    ITaskService service = mock(ITaskService.class);
    TaskMapper mapper = mock(TaskMapper.class);
    TaskController controller = new TaskController(service, mapper);

    TaskRequest req = new TaskRequest();
    req.setTitle("a");
    req.setDescription("b");
    req.setCompleted(false);

    when(mapper.toDomain(req)).thenReturn(taskWithId);
    when(service.create(taskWithId)).thenReturn(taskWithoutId);
    when(mapper.toResponse(taskWithoutId)).thenReturn(new TaskResponse(1L, "a", "b", false));

    var resp = controller.create(req);

    assertEquals(201, resp.getStatusCode().value());
    assertNotNull(resp.getBody());
    assertEquals(1L, resp.getBody().getId());
  }

  @Test
  void deleteTaskNotExistsTest() {
    ITaskService service = mock(ITaskService.class);
    TaskMapper mapper = mock(TaskMapper.class);
    TaskController controller = new TaskController(service, mapper);
    ResponseEntity<Void> response = controller.delete(1L);
    response.getBody();
  }

  @Test
  void deleteTaskExistsTest() {
    ITaskService service = mock(ITaskService.class);
    TaskMapper mapper = mock(TaskMapper.class);
    when(service.delete(anyLong())).thenReturn(true);
    TaskController controller = new TaskController(service, mapper);
    ResponseEntity<Void> response = controller.delete(1L);
    response.getBody();
  }
}
