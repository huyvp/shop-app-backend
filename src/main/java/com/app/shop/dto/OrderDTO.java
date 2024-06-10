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
    @Min(value = 1, message = "INVALID_ID")
    private Long userId;
    @JsonProperty("fullname")
    private String fullName;
    private String email;
    @JsonProperty("phone_number")
    @NotBlank(message = "INVALID_PHONE_NUMBER")
    private String phoneNumber;
    private String address;
    private String note;
    @JsonProperty("total_money")
    private float totalMoney;
    @JsonProperty("shipping_method")
    @NotBlank(message = "INVALID_ORDER_SHIP_METHOD")
    private String shippingMethod;
    @JsonProperty("shipping_address")
    @NotBlank(message = "INVALID_ORDER_SHIP_ADDRESS")
    private String shippingAddress;
    @JsonProperty("payment_method")
    @NotBlank(message = "INVALID_ORDER_PAYMENT_METHOD")
    private String paymentMethod;
}
