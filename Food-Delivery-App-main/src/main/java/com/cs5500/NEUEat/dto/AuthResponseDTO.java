package com.cs5500.NEUEat.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for authentication response after login/register
 */
@Schema(description = "Authentication response containing user details and status")
public class AuthResponseDTO {

    @Schema(description = "Success flag indicating if authentication was successful", example = "true")
    private boolean success;

    @Schema(description = "Message describing the result", example = "Login successful")
    private String message;

    @Schema(description = "User ID", example = "507f1f77bcf86cd799439011")
    private String userId;

    @Schema(description = "Username", example = "john_doe")
    private String userName;

    @Schema(description = "User type: CUSTOMER, RESTAURANT, or DRIVER", example = "CUSTOMER")
    private String type;

    @Schema(description = "Authentication token (if applicable)", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public AuthResponseDTO(boolean success, String message, String userId, String userName, String type) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.userName = userName;
        this.type = type;
    }

    public AuthResponseDTO(boolean success, String message, String userId, String userName, String type, String token) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.userName = userName;
        this.type = type;
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResponseDTO{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
