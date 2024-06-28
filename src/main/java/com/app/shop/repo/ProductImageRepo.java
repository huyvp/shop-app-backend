package com.app.shop.repo;

import com.app.shop.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProductId(Long productId);
}
