package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to reset a user's password")
public class PasswordResetRequest {

    @NotBlank
    @Schema(description = "User id", example = "507f1f77bcf86cd799439011")
    private String id;

    @Schema(description = "Current/old password", example = "oldPass123")
    private String oldPassword;

    @Schema(description = "New password", example = "newPass123")
    private String newPassword;

    public PasswordResetRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
