package com.app.shop.service;

import com.app.shop.dto.ProductDTO;
import com.app.shop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;


public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws IOException;

    Product getProductById(long id);

    Page<Product> getAllProducts(PageRequest pageRequest);

    Product updateProduct(long id, ProductDTO productDTO);

    void deleteProduct(long id);

    boolean existsByName(String name);
}
