package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Request to create a new restaurant")
public class RestaurantCreateRequest {

    @NotBlank(message = "Restaurant name is required")
    @Schema(description = "Name of the restaurant", example = "Pasta Paradise")
    private String restaurantName;

    @NotBlank(message = "Description is required")
    @Schema(description = "Restaurant description", example = "Authentic Italian cuisine")
    private String description;

    @NotBlank(message = "Address is required")
    @Schema(description = "Restaurant street address", example = "456 Oak Avenue")
    private String address;

    @NotBlank(message = "City is required")
    @Schema(description = "Restaurant's city", example = "Boston")
    private String city;

    @NotBlank(message = "State is required")
    @Schema(description = "Restaurant's state", example = "MA")
    private String state;

    @NotBlank(message = "Zip code is required")
    @Schema(description = "Restaurant's zip code", example = "02115")
    private String zip;

    @NotBlank(message = "Phone number is required")
    @Schema(description = "Restaurant phone number", example = "6171234567")
    private String phoneNumber;

    @NotEmpty(message = "At least one tag is required")
    @Schema(description = "Restaurant tags/cuisines", example = "[\"Italian\", \"Vegetarian\"]")
    private List<String> tags;

    public RestaurantCreateRequest() {
    }

    public RestaurantCreateRequest(String restaurantName, String description, String address,
                                   String city, String state, String zip, String phoneNumber, List<String> tags) {
        this.restaurantName = restaurantName;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.tags = tags;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "RestaurantCreateRequest{" +
                "restaurantName='" + restaurantName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
