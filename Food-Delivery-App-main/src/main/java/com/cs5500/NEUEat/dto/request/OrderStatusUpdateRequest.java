package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to update order status")
public class OrderStatusUpdateRequest {

    @NotBlank(message = "Order ID is required")
    @Schema(description = "ID of the order to update", example = "507f1f77bcf86cd799439011")
    private String orderId;

    @NotBlank(message = "Status is required")
    @Schema(description = "New order status", example = "CONFIRMED")
    private String status;

    public OrderStatusUpdateRequest() {
    }

    public OrderStatusUpdateRequest(String orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderStatusUpdateRequest{" +
                "orderId='" + orderId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
