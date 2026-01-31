package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Request to create a new dish/menu item")
public class DishCreateRequest {

    @NotBlank(message = "Dish name is required")
    @Schema(description = "Name of the dish", example = "  Margherita Pizza")
    private String dishName;

    @NotBlank(message = "Description is required")
    @Schema(description = "Dish description", example = "Fresh mozzarella and basil")
    private String description;

    @Positive(message = "Price must be greater than 0")
    @Schema(description = "Dish price in USD", example = "12.99")
    private Double price;

    @NotBlank(message = "Category is required")
    @Schema(description = "Dish category", example = "Pizza")
    private String category;

    @Schema(description = "Whether the dish is available", example = "true")
    private Boolean available;

    @NotEmpty(message = "At least one tag is required")
    @Schema(description = "Dish tags/ingredients", example = "[\"Vegetarian\", \"Gluten-Free\"]")
    private List<String> tags;

    public DishCreateRequest() {
    }

    public DishCreateRequest(String dishName, String description, Double price, String category, Boolean available, List<String> tags) {
        this.dishName = dishName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.available = available;
        this.tags = tags;
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
        return "DishCreateRequest{" +
                "dishName='" + dishName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
