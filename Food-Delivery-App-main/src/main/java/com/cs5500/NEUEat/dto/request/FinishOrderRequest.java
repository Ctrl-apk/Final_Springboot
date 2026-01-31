package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to mark an order as finished")
public class FinishOrderRequest {

    @NotBlank
    @Schema(description = "Order id", example = "507f1f77bcf86cd799439011")
    private String orderId;

    public FinishOrderRequest() {}

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
