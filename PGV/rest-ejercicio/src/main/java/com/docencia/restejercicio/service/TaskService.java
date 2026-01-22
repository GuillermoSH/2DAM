package com.docencia.restejercicio.service;

import com.docencia.restejercicio.model.Task;
import com.docencia.restejercicio.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Metodo para obtener todas las tareas
     * @return lista de tareas
     */
    public List<Task> getAll() {
        return repository.findAll();
    }

    /**
     * Metodo para obtener una tarea por id
     * @param id de la tarea a buscar
     * @return la tarea encontrada
     * @throws IllegalArgumentException si no existen tareas con el id
     */
    public Task getById(Long id) throws IllegalArgumentException {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("La tarea con ese id no existe"));
    }

    /**
     * Metodo para crear una nueva tarea
     * @param task tarea a crear
     * @return la tarea creada
     */
    public Task create(Task task) {
        return repository.save(task);
    }

    /**
     * Metodo para actualizar una tarea
     * @param id de la tarea a actualizar
     * @param update informacion de la tarea a cambiar
     * @return tarea actualizada
     */
    public Task update(Long id, Task update) {
        update.setId(id);
        return repository.save(update);
    }

    /**
     * Metodo para eliminar una tarea por el id
     * @param id de la tarea a eliminar
     * @return flag si elimina o no la tarea
     */
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }
}
