package com.app.shop.controller;

import com.app.shop.dto.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    @PostMapping
    public String createCategories(@RequestBody @Valid CategoryDTO categoryDTO) {
        return "Create category";
    }

    @GetMapping
    public String getAllCategories(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return "Get all category";
    }

    @GetMapping("{id}")
    public String getCategoriesById(@PathVariable int id) {
        return "Get category by id";
    }

    @DeleteMapping("{id}")
    public String deleteCategories(@Valid @PathVariable int id) {
        return "Delete category";
    }
}
