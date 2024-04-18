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
    @GetMapping("")
    public String getAllProduct(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return "Get all product";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id) {
        return "Product by id";
    }

    @PostMapping("")
    public String createProduct(@Valid @ModelAttribute ProductDTO productDTO) throws IOException {
        List<MultipartFile> files = productDTO.getFiles();
        files = files == null ? new ArrayList<>(0) : files;
        for (MultipartFile file : files) {
            if (file.getSize() == 0) continue;
            if (file.getSize() > 10 * 1048576) {
                throw new FileSizeException("File is too large! Maximum size is 10MB");
            }
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new FileFormatNotSupportException("File must be an image");
            }
            storeFile(file);
        }
        return "DONE";
    }

    private String storeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename() != null ? file.getOriginalFilename() : "");
        String uniqueFileName = generateUniqueName(filename);
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    private String generateUniqueName(String nameDefault) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE);
        String timestamp = dateFormat.format(new Date());
        return sb.append(timestamp).append(".png").toString();
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return "Delete Product";
    }
}
