package com.app.shop.dto.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductReq {
    @Size(min = 3, max = 200, message = "VALID_1006")
    private String name;
    @Min(value = 0, message = "VALID_1007")
    @Max(value = 10000000, message = "VALID_1007")
    private float price;
    private String thumbnail;
    private String description;
    @JsonProperty("category_id")
    private long categoryId;
}
