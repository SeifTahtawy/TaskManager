package com.seif.TaskManager.api.controller;

import com.seif.TaskManager.api.dto.request.LoginRequest;
import com.seif.TaskManager.api.dto.request.RegisterUserRequest;
import com.seif.TaskManager.api.dto.response.LoginResponse;
import com.seif.TaskManager.api.dto.response.RegisterUserResponse;
import com.seif.TaskManager.security.JwtService;
import com.seif.TaskManager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")

public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public RegisterUserResponse registerUser(@Valid @RequestBody RegisterUserRequest request) {
        userService.registerUser(request);
        return new RegisterUserResponse("User registered successfully");
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken
                                (request.getEmail(),
                                        request.getPassword()
                                )
                );
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return new LoginResponse("Login Successful", token);
    }

    @GetMapping("/me")
    public Map<String, String> getProfile(Authentication authentication) {
        return Map.of(
                "email", authentication.getName()
        );
    }

}