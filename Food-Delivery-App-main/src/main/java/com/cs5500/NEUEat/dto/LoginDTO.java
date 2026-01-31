package com.cs5500.NEUEat.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for user login request
 */
@Schema(description = "Login request with username and password")
public class LoginDTO {

    @Schema(description = "Username of the user", example = "john_doe", required = true)
    private String userName;

    @Schema(description = "Password of the user", example = "password123", required = true)
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
