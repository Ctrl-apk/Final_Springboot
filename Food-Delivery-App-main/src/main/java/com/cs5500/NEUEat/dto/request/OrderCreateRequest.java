package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Request to create a new order")
public class OrderCreateRequest {

    @NotBlank(message = "Restaurant ID is required")
    @Schema(description = "ID of the restaurant", example = "507f1f77bcf86cd799439011")
    private String restaurantId;

    @NotEmpty(message = "At least one dish must be selected")
    @Schema(description = "List of dish IDs to order", example = "[\"dish1\", \"dish2\"]")
    private List<String> dishIds;

    @NotBlank(message = "Delivery address is required")
    @Schema(description = "Delivery address for the order", example = "789 Elm Street, Boston, MA 02115")
    private String deliveryAddress;

    @Schema(description = "Payment mode (CASH or ONLINE)", example = "ONLINE")
    private String paymentMode;

    public OrderCreateRequest() {
    }

    public OrderCreateRequest(String restaurantId, List<String> dishIds, String deliveryAddress, String paymentMode) {
        this.restaurantId = restaurantId;
        this.dishIds = dishIds;
        this.deliveryAddress = deliveryAddress;
        this.paymentMode = paymentMode;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<String> getDishIds() {
        return dishIds;
    }

    public void setDishIds(List<String> dishIds) {
        this.dishIds = dishIds;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public String toString() {
        return "OrderCreateRequest{" +
                "restaurantId='" + restaurantId + '\'' +
                ", dishIds=" + dishIds +
                ", paymentMode='" + paymentMode + '\'' +
                '}';
    }
}
