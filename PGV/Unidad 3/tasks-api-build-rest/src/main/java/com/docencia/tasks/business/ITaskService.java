package com.docencia.tasks.business;

import com.docencia.tasks.adapters.in.api.TaskRequest;
import com.docencia.tasks.adapters.in.api.TaskResponse;
import com.docencia.tasks.domain.model.Task;

import java.util.List;

public interface ITaskService {
    Task createTask(Task task);
    List<Task> getTasks();
    Task getTaskById(Long id);
    Task updateTask(Task task);
    boolean deleteTask(Task task);
}
