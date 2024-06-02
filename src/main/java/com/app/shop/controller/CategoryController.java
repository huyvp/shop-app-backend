package com.app.shop.controller;

import com.app.shop.handler.ResponseHandler;
import com.app.shop.dto.CategoryDTO;
import com.app.shop.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Object> createCategories(@RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseHandler.returnObject(HttpStatus.CREATED, categoryService.createCategory(categoryDTO));
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        return ResponseHandler.returnObject(HttpStatus.OK, categoryService.getALlCategories());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getCategoriesById(@PathVariable int id) {
        return ResponseHandler.returnObject(HttpStatus.OK, categoryService.getCategoryById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCategories(@Valid @PathVariable int id, @RequestBody @Valid CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
        return ResponseHandler.returnMsg(HttpStatus.OK, "Update category successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategories(@Valid @PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseHandler.returnMsg(HttpStatus.OK, "Delete category successfully");
    }
}
