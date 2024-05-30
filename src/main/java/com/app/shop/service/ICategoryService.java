package com.app.shop.service;

import com.app.shop.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(Category category);
    Category getCategoryById(long id);
    List<Category> getALlCategories();
    Category updateCategory(long id, Category category);
    void deleteCategory(long id);
}
