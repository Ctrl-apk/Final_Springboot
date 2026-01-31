package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to add a comment to an order")
public class AddCommentRequest {

    @NotBlank
    @Schema(description = "Order id", example = "507f1f77bcf86cd799439011")
    private String orderId;

    @Schema(description = "Rating", example = "5")
    private int rating;

    @Schema(description = "Comment content", example = "Great food!")
    private String content;

    public AddCommentRequest() {}

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
