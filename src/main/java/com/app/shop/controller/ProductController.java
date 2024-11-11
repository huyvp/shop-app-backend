package com.app.shop.controller;

import com.app.shop.dto.request.product.ProductReq;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.dto.response.ProductResponse;
import com.app.shop.service.IProductService;
import com.github.javafaker.Faker;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    IProductService productService;

    @PostMapping("")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductReq productReq) throws IOException {
        return ResponseHandler.execute(
                productService.createProduct(productReq)
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllProduct(@RequestParam("page") int page,
                                           @RequestParam("limit") int limit) {
        Page<ProductResponse> productPage = productService.getAllProducts(
                PageRequest.of(page, limit, Sort.by("CreatedAt").ascending())
        );
        int totalPages = productPage.getSize();
        List<ProductResponse> products = productPage.getContent();
        return ResponseHandler.execute(products, totalPages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable int id) {
        return ResponseHandler.execute(
                productService.getProductById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable int id, @RequestBody ProductReq productReq) {
        productService.updateProduct(id, productReq);
        return ResponseHandler.execute();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseHandler.execute();
    }

    @PostMapping(value = "/uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadImage(@PathVariable @Valid Long id, @ModelAttribute("files") List<MultipartFile> files) throws IOException {
        return ResponseHandler.execute(
                productService.uploadImage(id, files)
        );
    }

    @PostMapping("/faker")
    public String genProducts() throws IOException {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            String name = faker.commerce().productName();
            if (productService.existsByName(name)) continue;
            ProductReq productReq = ProductReq.builder()
                    .name(name)
                    .price(faker.number().numberBetween(10, 90_000_000))
                    .description(faker.lorem().sentence())
                    .thumbnail("")
                    .categoryId(faker.number().numberBetween(2, 5))
                    .build();
            productService.createProduct(productReq);
        }
        return "DONE";
    }
}
