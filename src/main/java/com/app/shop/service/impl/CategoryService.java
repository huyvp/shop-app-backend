package com.app.shop.service.impl;

import com.app.shop.dto.request.CategoryReq;
import com.app.shop.entity.Category;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.repo.CategoryRepo;
import com.app.shop.service.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {
    CategoryRepo categoryRepo;

    @Override
    public Category createCategory(CategoryReq categoryReq) {
        Category category = Category.builder()
                .name(categoryReq.getName())
                .build();
        return categoryRepo.save(category);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.CATEGORY_3002));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public void updateCategory(long id, CategoryReq categoryReq) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(categoryReq.getName());
        categoryRepo.save(existingCategory);
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepo.deleteById(id);
    }
}
