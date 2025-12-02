package com.docencia.restejercicio.repository;

import com.docencia.restejercicio.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(Long id) {
        User foundUser = findUser(id);
        if (foundUser == null) return Optional.empty();
        return Optional.of(foundUser);
    }

    public User save(User user) {
        if (user.getId() == null) user.setId(UUID.randomUUID().getMostSignificantBits());
        if (findUser(user.getId())!=null) users.remove(user);
        users.add(user);
        return user;
    }

    public boolean deleteById(Long id) {
        for (User user : users) {
            if (Objects.equals(user.getId(), id)){
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    public boolean existsById(Long id) {
        return findUser(id) != null;
    }

    /**
     * Metodo auxiliar para buscar un usuario por id
     * @param id a buscar
     * @return el usuario o null
     */
    private User findUser(Long id) {
        for (User user : users) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return null;
    }
}
