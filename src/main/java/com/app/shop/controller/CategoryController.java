package com.app.shop.controller;

import com.app.shop.dto.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:1892
@RequestMapping("api/v1/categories")
public class CategoryController {
    @GetMapping("")
    public ResponseEntity<String> getAllCategories(@RequestParam("page") int  page, @RequestParam("limit") int limit){
        return ResponseEntity.ok("category");
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getCategoriesById(@PathVariable int id){
        return ResponseEntity.ok("category");
    }
    @PostMapping("")
    public ResponseEntity<?> insertCategories(@RequestBody @Valid CategoryDTO categoryDTO ,BindingResult result){
        if (result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok("category");
    }
}
