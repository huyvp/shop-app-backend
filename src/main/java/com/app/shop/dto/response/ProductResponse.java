package com.app.shop.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse extends BaseResponse {
    Long id;
    String name;
    float price;
    String thumbnail;
    String description;
    long categoryId;
}


