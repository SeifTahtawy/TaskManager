package com.seif.TaskManager.service;

import com.seif.TaskManager.api.dto.request.RegisterUserRequest;
import com.seif.TaskManager.domain.exception.UserAlreadyExistsException;
import com.seif.TaskManager.domain.model.User;
import com.seif.TaskManager.repository.UserRepository;
import com.seif.TaskManager.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MemberUserDetailsService userDetailsService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       MemberUserDetailsService userDetailsService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public void registerUser(RegisterUserRequest request) throws UserAlreadyExistsException {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("A user with this email already exists.");
        }

        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username Taken.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        userRepository.save(user);
    }


}