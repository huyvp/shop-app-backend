package com.app.shop.controller;

import com.app.shop.dto.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    @GetMapping("")
    public String getAllCategories(@RequestParam("page") int  page, @RequestParam("limit") int limit){
        return "category";
    }
    @GetMapping("{id}")
    public String getCategoriesById(@PathVariable int id){
        return "category";
    }
    @PostMapping("")
    public String insertCategories(@RequestBody @Valid CategoryDTO categoryDTO){

        return "category";
    }
}
