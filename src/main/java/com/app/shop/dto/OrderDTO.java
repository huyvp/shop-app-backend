package com.app.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDTO {
    @JsonProperty("user_id")
    @Min(value = 1, message = "User's id must be equal greater than zero")
    private Long userId;
    @JsonProperty("fullname")
    private String fullName;
    private String email;
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    private String address;
    private String note;
    @JsonProperty("total_money")
    private float totalMoney;
    @JsonProperty("shipping_method")
    @NotBlank(message = "Shipping method is required")
    private String shippingMethod;
    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
}
