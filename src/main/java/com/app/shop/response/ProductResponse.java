package com.app.shop.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductResponse extends BaseResponse {
    private Long id;
    private String name;
    private float price;
    private String thumbnail;
    private String description;
    @JsonProperty("category_id")
    private long categoryId;
}
