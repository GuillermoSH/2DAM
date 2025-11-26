package com.docencia.rest.service;

import com.docencia.rest.exception.ResourceNotFoundException;
import com.docencia.rest.model.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAllUsers();

    User getUserById(@PathVariable(value = "id") Integer userId) throws ResourceNotFoundException;

    User createUser(@Valid @RequestBody User user);

    User updateUser(@PathVariable(value = "id") Integer userId, @Valid @RequestBody User userDetails) throws ResourceNotFoundException;

    boolean deleteUser(@PathVariable(value = "id") Integer userId) throws ResourceNotFoundException;
}
