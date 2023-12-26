package org.example.controller;

import org.example.exceptions.NotFoundException;
import org.example.model.User;
import org.example.model.Vote;
import org.example.openapi.api.UsersApi;
import org.example.openapi.model.*;
import org.example.service.UserService;
import org.example.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements UsersApi {

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Override
    public ResponseEntity<UserCreateResponse> userCreate(UserCreateRequest userCreateRequest) {
        User user = userService.createUser();
        UserCreateResponse userCreateResponse = new UserCreateResponse();
        userCreateResponse.setMessage("Successfully created user " + user.getId());
        return new ResponseEntity<>(userCreateResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserLogoutResponse> logoutUser() {
        userService.logoutUser();
        UserLogoutResponse userLogoutResponse = new UserLogoutResponse();
        userLogoutResponse.setMessage("Successfully logged out");
        return new ResponseEntity<>(userLogoutResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(Long id) {
        User user = userService.getUserById(id);

        List<Vote> votes = voteService.getVotesByUser(user);

        UserResponse userResponse = mapToResponse(user, votes);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserResponse>> listUsers() {
        List<User> users = userService.findAll();
        List<UserResponse> result = users.stream()
                .map(user ->
                        mapToResponse(user, voteService.getVotesByUser(user))).toList();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserLoginResponse> loginUser(Long id) {
        if (userService.loginUser(id)) {
            UserLoginResponse userLoginResponse = new UserLoginResponse();
            userLoginResponse.setMessage("User " + id + " logged in");
            return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
        }
        throw new NotFoundException("User not found");
    }

    private static UserResponse mapToResponse(User user, List<Vote> votes) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getId());
        userResponse.setVotes(votes.stream().map(Vote::getId).toList());
        return userResponse;
    }
}
