package com.cs5500.NEUEat.controller;

import com.cs5500.NEUEat.dto.request.LoginRequest;
import com.cs5500.NEUEat.dto.request.RegisterRequest;
import com.cs5500.NEUEat.dto.response.AuthResponse;
import com.cs5500.NEUEat.model.User;
import com.cs5500.NEUEat.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for authentication operations (login and registration)
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Authentication", description = "Authentication APIs for login and registration")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Login endpoint
     * @param loginRequest containing username and password
     * @return AuthResponse with user details and success status
     */
    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Authenticate user with username and password")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUserName());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(false, "User not found"));
        }

        // For now, comparing plain text passwords. In production, use PasswordEncoder
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(false, "Invalid credentials"));
        }

        AuthResponse response = new AuthResponse(
                true,
                "Login successful",
                user.getId(),
                user.getUserName(),
                user.getType()
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Register endpoint
     * @param registerRequest containing user registration details
     * @return AuthResponse with created user details
     */
    @PostMapping("/register")
    @Operation(summary = "User Registration", description = "Register a new user account")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        // Check if user already exists
        User existingUser = userRepository.findByUserName(registerRequest.getUserName());
        if (existingUser != null) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(false, "Username already exists"));
        }

        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(false, "Passwords do not match"));
        }

        // Create new user
        User newUser = new User(
                registerRequest.getUserName(),
                registerRequest.getPassword(),
                registerRequest.getPhoneNumber(),
                registerRequest.getAddress(),
                registerRequest.getCity(),
                registerRequest.getState(),
                registerRequest.getZip()
        );
        newUser.setType(registerRequest.getType());

        User savedUser = userRepository.save(newUser);

        AuthResponse response = new AuthResponse(
                true,
                "Registration successful",
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getType()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Logout endpoint
     * @return success message
     */
    @PostMapping("/logout")
    @Operation(summary = "User Logout", description = "Logout the current user")
    public ResponseEntity<AuthResponse> logout() {
        return ResponseEntity.ok(new AuthResponse(true, "Logout successful"));
    }
}

