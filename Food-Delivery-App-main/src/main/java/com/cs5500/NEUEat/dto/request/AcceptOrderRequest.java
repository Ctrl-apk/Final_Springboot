package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request for a driver to accept an order")
public class AcceptOrderRequest {

    @NotBlank
    @Schema(description = "Order id", example = "507f1f77bcf86cd799439011")
    private String orderId;

    @NotBlank
    @Schema(description = "Driver id", example = "507f1f77bcf86cd799439012")
    private String driverId;

    public AcceptOrderRequest() {}

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
