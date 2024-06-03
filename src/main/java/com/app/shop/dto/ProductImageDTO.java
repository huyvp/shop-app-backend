package com.app.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private int productId;
    @JsonProperty(value = "image_id")
    private int imageId;
}
