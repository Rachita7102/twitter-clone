package com.example.twitter.controller;

import com.example.twitter.dao.UserResponse;
import com.example.twitter.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Assign a role to a user")
    @PutMapping("/assign-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> assignRole(@RequestParam String username,
                                                   @RequestParam String role) {
        try{
            authService.assignRole(username, role);
            return ResponseEntity.ok("Role '" + role + "' assigned to user '" + username + "' successfully.");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error assigning role: " + e.getMessage());
        }
    }
}
