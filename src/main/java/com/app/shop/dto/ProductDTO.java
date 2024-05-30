package com.app.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.app.shop.constant.DTOConstants.Product.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDTO {
    @Size(min = MIN_SIZE, max = MAX_SIZE, message = NAME_MSG)
    private String name;
    @Min(value = MIN_VALUE, message = MIN_MSG)
    @Max(value = MAX_VALUE, message = MAX_MSG)
    private float price;
    private String thumbnail;
    private String description;
    @JsonProperty("category_id")
    private String categoryId;
    private List<MultipartFile> files;
}
