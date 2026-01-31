package com.cs5500.NEUEat.dto.request;

import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to update a user's address")
public class AddressResetRequest {

    @NotBlank
    @Schema(description = "User id", example = "507f1f77bcf86cd799439011")
    private String id;

    @NotBlank
    @Schema(description = "Street address", example = "123 Main Street")
    private String address;

    @NotBlank
    @Schema(description = "City", example = "Boston")
    private String city;

    @NotBlank
    @Schema(description = "State", example = "MA")
    private String state;

    @NotBlank
    @Schema(description = "Zip code", example = "02115")
    private String zip;

    public AddressResetRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
