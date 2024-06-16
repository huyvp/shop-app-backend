package com.app.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1, message = "VALID_1000")
    private Long orderId;
    @JsonProperty("product_id")
    @Min(value = 1, message = "VALID_1000")
    private Long productId;
    private Long price;
    @JsonProperty("number_of_product")
    @Min(value = 1, message = "VALID_1011")
    private int numberOfProducts;
    @JsonProperty("total_money")
    private int totalMoney;
    private String color;
}
