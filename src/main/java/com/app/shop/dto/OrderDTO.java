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
    @Min(value = 1, message = "VALID_1000")
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("fullname")
    private String fullName;
    private String email;
    @JsonProperty("phone_number")
    @NotBlank(message = "VALID_1002")
    private String phoneNumber;
    private String address;
    private String note;
    @JsonProperty("total_money")
    private float totalMoney;
    @JsonProperty("shipping_method")
    @NotBlank(message = "VALID_1008")
    private String shippingMethod;
    @JsonProperty("shipping_address")
    @NotBlank(message = "VALID_1009")
    private String shippingAddress;
    @JsonProperty("payment_method")
    @NotBlank(message = "VALID_1010")
    private String paymentMethod;
}
