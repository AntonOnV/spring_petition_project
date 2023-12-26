package org.example.repository;

import org.example.model.User;

import java.util.Optional;

public interface UserRepository {

    User saveUser(User user);

    Optional<User> getUserById(String id);
}
