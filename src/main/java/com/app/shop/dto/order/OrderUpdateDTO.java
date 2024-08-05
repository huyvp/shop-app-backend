package com.app.shop.dto.order;

import com.app.shop.validator.DateAfterNow;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderUpdateDTO {
    @Min(value = 1, message = "VALID_1000")
    @JsonProperty("user_id")
    Long userId;
    @JsonProperty("fullname")
    @NotBlank(message = "VALID_1001")
    String fullName;
    String email;
    @JsonProperty("phone_number")
    @NotBlank(message = "VALID_1002")
    String phoneNumber;
    @NotBlank(message = "VALID_1013")
    String address;
    String note;
    @JsonProperty("total_money")
    float totalMoney;
    @JsonProperty("shipping_method")
    @NotBlank(message = "VALID_1008")
    String shippingMethod;
    @JsonProperty("shipping_address")
    @NotBlank(message = "VALID_1009")
    String shippingAddress;
    @JsonProperty("shipping_date")
    @DateAfterNow(message = "VALID_1012")
    Date shippingDate;
    @JsonProperty("payment_method")
    @NotBlank(message = "VALID_1010")
    String paymentMethod;
}
