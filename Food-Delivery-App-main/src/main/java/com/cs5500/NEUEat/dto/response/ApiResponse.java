package com.cs5500.NEUEat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Generic API response wrapper with generic data type")
public class ApiResponse<T> {

    @Schema(description = "Success status of the API call", example = "true")
    private boolean success;

    @Schema(description = "Response message", example = "Operation completed successfully")
    private String message;

    @Schema(description = "Response data (type depends on endpoint)")
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
