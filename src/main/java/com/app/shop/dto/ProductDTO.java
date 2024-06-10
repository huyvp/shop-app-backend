package com.app.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.app.shop.constant.DTOConstants.Product.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDTO {
    @Size(min = MIN_SIZE, max = MAX_SIZE, message = "INVALID_PRODUCT_NAME")
    private String name;
    @Min(value = MIN_VALUE, message = "INVALID_PRODUCT_PRICE")
    @Max(value = MAX_VALUE, message = "INVALID_PRODUCT_PRICE")
    private float price;
    private String thumbnail;
    private String description;
    @JsonProperty("category_id")
    private long categoryId;
}
