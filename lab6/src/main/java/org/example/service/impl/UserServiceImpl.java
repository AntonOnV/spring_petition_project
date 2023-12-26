package org.example.service.impl;

import org.example.config.CurrentUser;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentUser currentUser;

    @Override
    public User createUser() {
        User user = new User();
        currentUser.setUser(user);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Boolean loginUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            currentUser.setUser(user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean logoutUser() {
        currentUser.setUser(null);
        return true;
    }
}
