package com.app.shop.service;

import com.app.shop.dto.CategoryDTO;
import com.app.shop.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);

    Category getCategoryById(long id);

    List<Category> getALlCategories();

    void updateCategory(long id, CategoryDTO categoryDTO);

    void deleteCategory(long id);
}
