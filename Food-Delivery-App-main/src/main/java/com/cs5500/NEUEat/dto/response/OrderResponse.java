package com.cs5500.NEUEat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Response containing order details")
public class OrderResponse {

    @Schema(description = "Order ID", example = "507f1f77bcf86cd799439011")
    private String orderId;

    @Schema(description = "Customer ID", example = "507f1f77bcf86cd799439012")
    private String customerId;

    @Schema(description = "Restaurant ID", example = "507f1f77bcf86cd799439013")
    private String restaurantId;

    @Schema(description = "List of dishes in the order")
    private List<DishResponse> dishes;

    @Schema(description = "Total order amount in USD", example = "45.99")
    private Double totalAmount;

    @Schema(description = "Current order status", example = "CONFIRMED")
    private String status;

    @Schema(description = "Order creation timestamp", example = "2024-01-27T10:30:00Z")
    private String createdAt;

    public OrderResponse() {
    }

    public OrderResponse(String orderId, String customerId, String restaurantId, List<DishResponse> dishes,
                        Double totalAmount, String status, String createdAt) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.dishes = dishes;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<DishResponse> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishResponse> dishes) {
        this.dishes = dishes;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
