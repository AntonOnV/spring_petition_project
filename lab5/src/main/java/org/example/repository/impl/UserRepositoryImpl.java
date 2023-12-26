package org.example.repository.impl;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public User saveUser(User user) {
        jdbcTemplate.update(
                "INSERT INTO USERS VALUES (?)", user.getId());

        return user;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from users where id=?", new Object[] {
                        id
                },
                new BeanPropertyRowMapper<>(User.class)));
    }
}
