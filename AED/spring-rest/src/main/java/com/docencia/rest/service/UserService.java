package com.docencia.rest.service;

import com.docencia.rest.exception.ResourceNotFoundException;
import com.docencia.rest.model.User;
import com.docencia.rest.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(@PathVariable(value = "id") Integer userId) throws ResourceNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    }

    @Override
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(@PathVariable(value = "id") Integer userId, @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        user.setName(userDetails.getName());
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(@PathVariable(value = "id") Integer userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        userRepository.delete(user);
        return !userRepository.existsById(userId);
    }
}
