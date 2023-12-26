package org.example.service;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private User user;

    @Autowired
    public void setUserRepository(User user) {
        this.user = user;
    }

    public User getDummyUser() {
        User dummyUser = user;
        dummyUser.setId(UUID.randomUUID().toString());
        return dummyUser;
    }

}
