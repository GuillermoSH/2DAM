package com.docencia.restejercicio.service;

import com.docencia.restejercicio.model.Task;
import com.docencia.restejercicio.model.User;
import com.docencia.restejercicio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Metodo para obtener todos los usuarios
     * @return lista de usuarios
     */
    public List<User> getAll() {
        return repository.findAll();
    }

    /**
     * Metodo para obtener un usuario por id
     * @param id del usuario a buscar
     * @return el usuario encontrado
     * @throws IllegalArgumentException si no existen usuarios con el id
     */
    public User getById(Long id) throws IllegalArgumentException {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("El usuario con ese id no existe"));
    }

    /**
     * Metodo para crear un nuevo usuario
     * @param user usuario a crear
     * @return el usuario creado
     */
    public User create(User user) {
        return repository.save(user);
    }

    /**
     * Metodo para actualizar un usuario
     * @param id del usuario a actualizar
     * @param update informacion del usuario a cambiar
     * @return usuario actualizado
     */
    public User update(Long id, User update) {
        update.setId(id);
        return repository.save(update);
    }

    /**
     * Metodo para eliminar un usuario por el id
     * @param id del usuario a eliminar
     * @return flag si elimina o no el usuario
     */
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }
}
