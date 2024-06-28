package com.app.shop.repo;

import com.app.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}
