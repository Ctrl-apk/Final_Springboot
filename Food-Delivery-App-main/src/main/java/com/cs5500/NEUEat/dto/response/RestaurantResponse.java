package com.cs5500.NEUEat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Response containing restaurant details")
public class RestaurantResponse {

    @Schema(description = "Restaurant ID", example = "507f1f77bcf86cd799439011")
    private String restaurantId;

    @Schema(description = "Name of the restaurant", example = "Pasta Paradise")
    private String restaurantName;

    @Schema(description = "Restaurant description", example = "Authentic Italian cuisine")
    private String description;

    @Schema(description = "Restaurant street address", example = "456 Oak Avenue")
    private String address;

    @Schema(description = "Restaurant's city", example = "Boston")
    private String city;

    @Schema(description = "Restaurant's state", example = "MA")
    private String state;

    @Schema(description = "Restaurant phone number", example = "6171234567")
    private String phoneNumber;

    @Schema(description = "Restaurant tags/cuisines", example = "[\"Italian\", \"Vegetarian\"]")
    private List<String> tags;

    @Schema(description = "Average restaurant rating", example = "4.5")
    private Double rating;

    public RestaurantResponse() {
    }

    public RestaurantResponse(String restaurantId, String restaurantName, String description, String address,
                             String city, String state, String phoneNumber, List<String> tags, Double rating) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.tags = tags;
        this.rating = rating;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RestaurantResponse{" +
                "restaurantId='" + restaurantId + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", city='" + city + '\'' +
                ", rating=" + rating +
                '}';
    }
}
