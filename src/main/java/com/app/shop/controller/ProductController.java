package com.app.shop.controller;

import com.app.shop.dto.ProductDTO;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.models.Product;
import com.app.shop.response.ProductResponse;
import com.app.shop.service.IProductService;
import com.github.javafaker.Faker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDTO productDTO) throws IOException {
        return ResponseHandler.returnObject(HttpStatus.CREATED, productService.createProduct(productDTO));
    }

    @PostMapping(value = "/uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadImage(@PathVariable @Valid Long id, @ModelAttribute("files") List<MultipartFile> files) throws IOException {
        return ResponseHandler.returnObject(HttpStatus.CREATED, productService.uploadImage(id, files));
    }

    @GetMapping
    public ResponseEntity<Object> getAllProduct(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        Page<ProductResponse> productPage = productService.getAllProducts(
                PageRequest.of(page, limit, Sort.by("CreatedAt").descending())
        );
        int totalPages = productPage.getSize();
        List<ProductResponse> products = productPage.getContent();
        return ResponseHandler.returnList(HttpStatus.OK, products, totalPages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        return ResponseHandler.returnObject(HttpStatus.OK, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        try {
            Product product = productService.getProductById(id);
            productService.deleteProduct(id);
            return ResponseHandler.returnBase(HttpStatus.OK);
        } catch (Exception ex) {
            throw new RuntimeException("Can't remove this product");
        }
    }

    @PostMapping("/faker")
    public String genProducts() throws IOException {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            String name = faker.commerce().productName();
            if (productService.existsByName(name)) continue;
            ProductDTO productDTO = ProductDTO.builder()
                    .name(name)
                    .price(faker.number().numberBetween(10, 90_000_000))
                    .description(faker.lorem().sentence())
                    .thumbnail("")
                    .categoryId(faker.number().numberBetween(2, 5))
                    .build();
            productService.createProduct(productDTO);
        }
        return "DONE";
    }
}
