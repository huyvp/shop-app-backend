package com.app.shop.service;

import com.app.shop.dto.request.CategoryReq;
import com.app.shop.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryReq categoryReq);

    Category getCategoryById(long id);

    List<Category> getAllCategories();

    void updateCategory(long id, CategoryReq categoryReq);

    void deleteCategory(long id);
}
