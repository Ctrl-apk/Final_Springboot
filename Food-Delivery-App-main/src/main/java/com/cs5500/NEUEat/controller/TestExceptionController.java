package com.cs5500.NEUEat.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestExceptionController {

    @GetMapping("/validation-error")
    public String testValidationError(@RequestParam(required = true) String email) {
        return "Valid request";
    }

    @GetMapping("/illegal-argument")
    public String testIllegalArgument(@RequestParam int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        return "Valid ID: " + id;
    }

    @GetMapping("/access-denied")
    public String testAccessDenied() {
        throw new AccessDeniedException("You do not have permission to access this resource");
    }

    @GetMapping("/server-error")
    public String testServerError() {
        throw new RuntimeException("Something went wrong!");
    }
}
