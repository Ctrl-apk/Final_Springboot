package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to update a user's phone number")
public class PhoneResetRequest {

    @NotBlank
    @Schema(description = "User id", example = "507f1f77bcf86cd799439011")
    private String id;

    @NotBlank
    @Schema(description = "New phone number", example = "6171234567")
    private String phoneNumber;

    public PhoneResetRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
