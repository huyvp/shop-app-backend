package com.app.shop.controller;

import com.app.shop.exception.media.FileFormatNotSupportException;
import com.app.shop.exception.media.FileSizeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

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
    public String createProduct(@RequestPart("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            // Check file size > 10MB = 1048576 Bytes
            if (file.getSize() > 2 * 1048576) {
                throw new FileSizeException("File is too large! Maximum size is 10MB");
            }
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new FileFormatNotSupportException("File must be an image");
            }
            String fileName = storeFile(file);
        }
        return "Create Product";
    }

    private String storeFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString().toLowerCase() + "_" + filename;
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        Files.copy(file.getInputStream(),destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return "Delete Product";
    }
}
