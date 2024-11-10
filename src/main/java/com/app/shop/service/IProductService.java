package com.app.shop.service;

import com.app.shop.dto.request.product.ProductReq;
import com.app.shop.entity.ProductImage;
import com.app.shop.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface IProductService {
    ProductResponse createProduct(ProductReq productReq) throws IOException;

    ProductResponse getProductById(long id);

    Page<ProductResponse> getAllProducts(PageRequest pageRequest);

    void updateProduct(long id, ProductReq productReq);

    void deleteProduct(long id);

    boolean existsByName(String name);

    List<ProductImage> uploadImage(Long id, List<MultipartFile> files) throws IOException;
}
