package com.app.shop.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse extends BaseResponse {
    private Long id;
    private String name;
    private float price;
    private String thumbnail;
    private String description;
    private long categoryId;
}
