package com.app.shop.controller;

import com.app.shop.dto.ProductDTO;
import com.app.shop.exception.FileFormatNotSupportException;
import com.app.shop.exception.FileSizeException;
import jakarta.validation.Valid;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.app.shop.constant.Constants.Pattern.DATE;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    @PostMapping("")
    public String createProduct(@Valid @ModelAttribute ProductDTO productDTO) throws IOException {
        return "DONE";
    }
    @GetMapping
    public String getAllProduct(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return "Get all product";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id) {
        return "Product by id";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return "Delete Product";
    }
}
