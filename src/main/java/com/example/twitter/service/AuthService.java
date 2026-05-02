package com.example.twitter.service;

import com.example.twitter.dao.*;
import com.example.twitter.mapper.UserMapper;
import com.example.twitter.repository.AuthRepository;
import com.example.twitter.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserMapper userMapper;
    private final AuthRepository authRepository;
    @Autowired
    private final JwtUtil jwtTokenUtil = new JwtUtil(); // For token generation

    public AuthService(UserMapper userMapper, AuthRepository authRepository) {
        this.userMapper = userMapper;
        this.authRepository = authRepository;
    }

    public UserResponse registerUser(Register request) {

        // Create entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // will hash later

        // Save to database
        User userRegistered = authRepository.save(user);

        // Convert to response DTO
        return userMapper.toResponse(userRegistered);

    }

    public AuthResponse loginUser(Login request) {

        // Fetch user from database
        User user = authRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate password (will hash later)
        if (!user.getPassword().equals(request.getPassword())) {
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
}
