package com.app.shop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.app.shop.constant.DTOConstants.Category.EMPTY_MSG;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
    @NotEmpty(message = EMPTY_MSG)
    private String name;
}
