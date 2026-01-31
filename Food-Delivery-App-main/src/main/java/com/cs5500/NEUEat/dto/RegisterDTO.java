package com.cs5500.NEUEat.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for user registration request
 */
@Schema(description = "Registration request with user details")
public class RegisterDTO {

    @Schema(description = "Username for the new account", example = "john_doe", required = true)
    private String userName;

    @Schema(description = "Password for the new account", example = "password123", required = true)
    private String password;

    @Schema(description = "Confirm password - must match password field", example = "password123", required = true)
    private String confirmPassword;

    @Schema(description = "Phone number of the user", example = "1234567890", required = true)
    private String phoneNumber;

    @Schema(description = "Street address of the user", example = "123 Main St", required = true)
    private String address;

    @Schema(description = "City of the user", example = "Boston", required = true)
    private String city;

    @Schema(description = "State of the user", example = "MA", required = true)
    private String state;

    @Schema(description = "ZIP code of the user", example = "02101", required = true)
    private String zip;

    @Schema(description = "User type: CUSTOMER, RESTAURANT, or DRIVER", example = "CUSTOMER", required = true)
    private String type;

    public RegisterDTO() {
    }

    public RegisterDTO(String userName, String password, String confirmPassword, String phoneNumber,
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
        return "RegisterDTO{" +
                "userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
