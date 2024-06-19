package com.app.shop.mapper;

import com.app.shop.dto.product.ProductDTO;
import com.app.shop.models.Product;
import com.app.shop.response.ProductResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    Product toProduct(ProductDTO productDTO);
    ProductResponse toProductResponse(Product product);
}
