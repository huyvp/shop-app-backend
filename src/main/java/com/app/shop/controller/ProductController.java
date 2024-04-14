package com.app.shop.controller;

import com.app.shop.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @GetMapping("")
    public String getAllProduct(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return "Get all product";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id) {
        return "Product by id";
    }

    @PostMapping("")
    public String createProduct(@RequestBody @Valid ProductDTO productDTO, @RequestPart("file") MultipartFile file) {
        return "Create Product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return "Delete Product";
    }
}
