package com.app.shop.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private long id;
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("fullname")
    private String fullName;
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;
    private String note;
    @JsonProperty("order_date")
    private LocalDateTime orderDate;
    @JsonProperty("total_money")
    private float totalMoney;
    @JsonProperty("shipping_method")
    private String shippingMethod;
    @JsonProperty("shipping_address")
    private String shippingAddress;
    @JsonProperty("shipping_date")
    private Date shippingDate;
    @JsonProperty("payment_method")
    private String paymentMethod;
}
