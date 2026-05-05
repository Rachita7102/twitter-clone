package com.example.twitter.service;

import com.example.twitter.dao.*;
import com.example.twitter.mapper.UserMapper;
import com.example.twitter.repository.AuthRepository;
import com.example.twitter.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserMapper userMapper;
    private final AuthRepository authRepository;
    private final JwtUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserMapper userMapper, AuthRepository authRepository, JwtUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.authRepository = authRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(Register request) {

        // Create entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole("USER");

        //Hash password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save to database
        User userRegistered = authRepository.save(user);

        // Convert to response DTO
        return userMapper.toResponse(userRegistered);

    }

    public AuthResponse loginUser(Login request) {

        // Fetch user from database
        User user = authRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate hashed password
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate token (will implement later)
        String token =jwtTokenUtil.generateToken(user.getUsername());

        // Build response
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUsername(user.getUsername());
        return response;

    }

    public void assignRole(String username, String role) {

        User user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(role);
        authRepository.save(user);

    }
}
