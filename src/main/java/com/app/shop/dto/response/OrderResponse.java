package com.app.shop.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

import static com.app.shop.constant.Constants.Pattern.DATE;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("user")
    private UserResponse userResponse;
    @JsonProperty("fullname")
    private String fullName;
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;
    private String note;
    @JsonProperty("order_date")
    @JsonFormat(pattern = DATE)
    private LocalDateTime orderDate;
    @JsonProperty("total_money")
    private float totalMoney;
    @JsonProperty("shipping_method")
    private String shippingMethod;
    @JsonProperty("shipping_address")
    private String shippingAddress;
    @JsonProperty("shipping_date")
    @JsonFormat(pattern = DATE)
    private Date shippingDate;
    @JsonProperty("payment_method")
    private String paymentMethod;
}
