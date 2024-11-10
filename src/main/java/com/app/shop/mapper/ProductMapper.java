package com.app.shop.mapper;

import com.app.shop.dto.request.product.ProductReq;
import com.app.shop.entity.Product;
import com.app.shop.dto.response.ProductResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductReq productReq);

    @Mapping(source = "category.id", target = "categoryId")
    ProductResponse toProductResponse(Product product);

    @AfterMapping
    default void handleAfterMapping(@MappingTarget ProductResponse productResponse, Product product) {
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setCreatedAt(product.getUpdatedAt());
    }
}
