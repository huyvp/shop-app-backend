package com.app.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @GetMapping("")
    public ResponseEntity<?> getAllProduct(@RequestParam("page") int page, @RequestParam("limit") int limit){
        return ResponseEntity.ok("Product");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){
        return ResponseEntity.ok("Product by id");
    }
    @PostMapping("")
    public ResponseEntity<?> insertProduct(){
        return ResponseEntity.ok("Product");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        return ResponseEntity.ok("Product");
    }
}
