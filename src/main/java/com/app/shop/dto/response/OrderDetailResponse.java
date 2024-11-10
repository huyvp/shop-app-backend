package com.app.shop.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    Long id;
    @JsonProperty("order_id")
    Long orderId;
    @JsonProperty("product_id")
    Long productId;
    Float price;
    @JsonProperty("number_of_product")
    int numberOfProduct;
    @JsonProperty("total_money")
    int totalMoney;
    String color;
}
