package com.app.shop.controller;

import com.app.shop.dto.CategoryDTO;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<Object> createCategories(@RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseHandler.execute(
                categoryService.createCategory(categoryDTO)
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        return ResponseHandler.execute(
                categoryService.getALlCategories()
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
                                              @RequestBody @Valid CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
        return ResponseHandler.execute(null);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategories(@Valid @PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseHandler.execute(null);
    }
}
