package com.app.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductImageDTO {
    @JsonProperty(value = "product_id")
    @Min(value = 1, message = "VALID_1000")
    private int productId;
    @JsonProperty(value = "image_id")
    @Size(min = 5, max = 200, message = "VALID_1004")
    private String imageUrl;
}
