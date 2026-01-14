package com.docencia.tasks.business;

import com.docencia.tasks.adapters.mapper.TaskMapper;
import com.docencia.tasks.adapters.out.persistence.TaskRepository;
import com.docencia.tasks.domain.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public Task createTask(Task task) {
        return taskMapper.toTask(taskRepository.save(taskMapper.toEntity(task)));
    }

    @Override
    public List<Task> getTasks() {
        return taskMapper.entityListToTasks(taskRepository.findAll());
    }

    @Override
    public Task getTaskById(Long id) {
        return taskMapper.toTask(taskRepository.findById(id).orElse(null));
    }

    @Override
    public Task updateTask(Task task) {
        return taskMapper.toTask(taskRepository.save(taskMapper.toEntity(task)));
    }

    @Override
    public boolean deleteTask(Task task) {
        taskRepository.delete(taskMapper.toEntity(task));
        return true;
    }
}
