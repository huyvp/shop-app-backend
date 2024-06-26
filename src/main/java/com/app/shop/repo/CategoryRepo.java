package com.app.shop.repo;

import com.app.shop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> { }
