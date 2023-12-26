package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    User createUser();

    User getUserById(Long id);

    List<User> findAll();

    Boolean loginUser(Long id);

    Boolean logoutUser();
}
