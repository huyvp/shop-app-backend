package com.app.shop.mapper;

import com.app.shop.dto.product.ProductDTO;
import com.app.shop.models.Product;
import com.app.shop.response.ProductResponse;
import lombok.Builder;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    Product toProduct(ProductDTO productDTO);

    @Mapping(source = "category.id", target = "categoryId")
    ProductResponse toProductResponse(Product product);

    @AfterMapping
    default void afterMappingHandle(Product product, @MappingTarget ProductResponse productResponse) {
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setUpdatedAt(product.getUpdatedAt());
    }
}
