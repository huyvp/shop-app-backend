package com.app.shop.service;

import com.app.shop.dto.ProductDTO;
import com.app.shop.models.Product;
import com.app.shop.models.ProductImage;
import com.app.shop.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws IOException;

    List<ProductImage> uploadImage(Long id, List<MultipartFile> files) throws IOException;

    Product getProductById(long id);

    Page<ProductResponse> getAllProducts(PageRequest pageRequest);

    Product updateProduct(long id, ProductDTO productDTO);

    void deleteProduct(long id);

    boolean existsByName(String name);
}
