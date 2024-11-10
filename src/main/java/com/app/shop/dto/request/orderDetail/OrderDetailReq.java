package com.app.shop.dto.request.orderDetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailReq {
    @JsonProperty("order_id")
    @Min(value = 1, message = "VALID_1000")
    Long orderId;
    @JsonProperty("product_id")
    @Min(value = 1, message = "VALID_1000")
    Long productId;
    float price;
    @JsonProperty("number_of_product")
    @Min(value = 1, message = "VALID_1011")
    int numberOfProduct;
    @JsonProperty("total_money")
    int totalMoney;
    String color;
}
