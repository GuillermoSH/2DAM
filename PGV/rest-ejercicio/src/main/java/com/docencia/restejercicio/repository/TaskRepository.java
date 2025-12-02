package com.docencia.restejercicio.repository;

import com.docencia.restejercicio.model.Task;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {
    List<Task> tasks = new ArrayList<>();

    public List<Task> findAll() {
        return tasks;
    }

    public Optional<Task> findById(Long id) {
        Task foundTask = findTask(id);
        if (foundTask == null) return Optional.empty();
        return Optional.of(foundTask);
    }

    public Task save(Task task) {
        if (task.getId() == null) task.setId(UUID.randomUUID().getMostSignificantBits());
        if (findTask(task.getId())!=null) tasks.remove(task);
        tasks.add(task);
        return task;
    }

    public boolean deleteById(Long id) {
        for (Task task : tasks) {
            if (Objects.equals(task.getId(), id)) {
                tasks.remove(task);
                return true;
            }
        }
        return false;
    }

    public boolean existsById(Long id) {
        return findTask(id) != null;
    }

    /**
     * Metodo auxiliar para buscar una tarea por id
     * @param id a buscar
     * @return la tarea o null
     */
    private Task findTask(Long id) {
        for (Task task : tasks) {
            if (Objects.equals(task.getId(), id)) {
                return task;
            }
        }
        return null;
    }
}
