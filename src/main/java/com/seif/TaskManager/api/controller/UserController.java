package com.seif.TaskManager.api.controller;

import com.seif.TaskManager.api.dto.request.RegisterUserRequest;
import com.seif.TaskManager.api.dto.response.RegisterUserResponse;
import com.seif.TaskManager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public RegisterUserResponse registerUser(@Valid @RequestBody RegisterUserRequest request) {
        userService.registerUser(request);
        return new RegisterUserResponse("User registered successfully");
    }
}