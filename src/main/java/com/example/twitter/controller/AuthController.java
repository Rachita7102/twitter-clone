package com.example.twitter.controller;

import com.example.twitter.dao.AuthResponse;
import com.example.twitter.dao.Login;
import com.example.twitter.dao.Register;
import com.example.twitter.dao.UserResponse;
import com.example.twitter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody Register request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody Login request) {
        return ResponseEntity.ok(authService.loginUser(request));
    }

    @GetMapping("/admin")
    public String testAdminRole() {
        return "Authenticated endpoint accessed successfully!";
    }

    @GetMapping("/user")
    public String testUserRole() {
        return "Authenticated endpoint accessed successfully!";
    }
}
