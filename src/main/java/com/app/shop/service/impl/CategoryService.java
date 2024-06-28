package com.app.shop.service.impl;

import com.app.shop.dto.CategoryDTO;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.entity.Category;
import com.app.shop.repo.CategoryRepo;
import com.app.shop.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepo.save(category);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.CATEGORY_3002));
    }

    @Override
    public List<Category> getALlCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public void updateCategory(long id, CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(categoryDTO.getName());
        categoryRepo.save(existingCategory);
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepo.deleteById(id);
    }
}
