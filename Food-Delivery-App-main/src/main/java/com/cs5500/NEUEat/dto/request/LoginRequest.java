package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Login request with user credentials")
public class LoginRequest {

    @NotBlank(message = "Username is required")
    @Schema(description = "User's username", example = "john_doe")
    private String userName;

    @NotBlank(message = "Password is required")
    @Schema(description = "User's password", example = "password123")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String userName, String password) {
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
        return "LoginRequest{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
