package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Registration request with user details")
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Schema(description = "User's username", example = "john_doe")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Schema(description = "User's password (minimum 6 characters)", example = "password123")
    private String password;

    @NotBlank(message = "Password confirmation is required")
    @Schema(description = "Password confirmation", example = "password123")
    private String confirmPassword;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    @Schema(description = "User's phone number (10 digits)", example = "5551234567")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Schema(description = "User's street address", example = "123 Main Street")
    private String address;

    @NotBlank(message = "City is required")
    @Schema(description = "User's city", example = "Boston")
    private String city;

    @NotBlank(message = "State is required")
    @Schema(description = "User's state", example = "MA")
    private String state;

    @NotBlank(message = "Zip code is required")
    @Schema(description = "User's zip code", example = "02115")
    private String zip;

    @Schema(description = "User type (role)", example = "CUSTOMER", allowableValues = {"CUSTOMER", "RESTAURANT", "DRIVER"})
    private String type;

    public RegisterRequest() {
    }

    public RegisterRequest(String userName, String password, String confirmPassword, String phoneNumber,
                          String address, String city, String state, String zip, String type) {
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
