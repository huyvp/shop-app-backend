package com.app.shop.controller;

import com.app.shop.dto.request.CategoryReq;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<Object> createCategories(@RequestBody @Valid CategoryReq categoryReq) {
        return ResponseHandler.execute(
                categoryService.createCategory(categoryReq)
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        return ResponseHandler.execute(
                categoryService.getAllCategories()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getCategoriesById(@PathVariable int id) {
        return ResponseHandler.execute(
                categoryService.getCategoryById(id)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCategories(@Valid @PathVariable int id,
                                                   @RequestBody @Valid CategoryReq categoryReq) {
        categoryService.updateCategory(id, categoryReq);
        return ResponseHandler.execute();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategories(@Valid @PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseHandler.execute();
    }
}
