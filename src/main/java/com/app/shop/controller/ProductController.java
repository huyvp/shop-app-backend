package com.app.shop.controller;

import com.app.shop.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @GetMapping("")
    public String getAllProduct(@RequestParam("page") int page, @RequestParam("limit") int limit){
        return "Get all product";
    }
    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id){
        return "Product by id";
    }
    @PostMapping("")
    public String createProduct(@RequestBody @Valid ProductDTO productDTO){
        return "Create Product";
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        return "Delete Product";
    }
}
