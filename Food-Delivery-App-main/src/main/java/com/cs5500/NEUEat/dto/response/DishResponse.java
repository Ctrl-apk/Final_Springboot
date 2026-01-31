package com.cs5500.NEUEat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Response containing dish/menu item details")
public class DishResponse {

    @Schema(description = "Dish ID", example = "507f1f77bcf86cd799439011")
    private String dishId;

    @Schema(description = "Name of the dish", example = "Margherita Pizza")
    private String dishName;

    @Schema(description = "Dish description", example = "Fresh mozzarella and basil")
    private String description;

    @Schema(description = "Dish price in USD", example = "12.99")
    private Double price;

    @Schema(description = "Dish category", example = "Pizza")
    private String category;

    @Schema(description = "Whether the dish is available", example = "true")
    private Boolean available;

    @Schema(description = "Dish tags/ingredients", example = "[\"Vegetarian\", \"Gluten-Free\"]")
    private List<String> tags;

    public DishResponse() {
    }

    public DishResponse(String dishId, String dishName, String description, Double price, String category, Boolean available, List<String> tags) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.available = available;
        this.tags = tags;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "DishResponse{" +
                "dishId='" + dishId + '\'' +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
