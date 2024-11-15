package com.app.shop.service.impl;

import com.app.shop.dto.request.product.ProductReq;
import com.app.shop.entity.Category;
import com.app.shop.entity.Product;
import com.app.shop.entity.ProductImage;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.ProductMapper;
import com.app.shop.repo.CategoryRepo;
import com.app.shop.repo.ProductImageRepo;
import com.app.shop.repo.ProductRepo;
import com.app.shop.dto.response.ProductResponse;
import com.app.shop.service.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

import static com.app.shop.constant.Constants.Common.MAXIMUM_IMAGES_PER_PRODUCT;
import static com.app.shop.constant.Constants.Pattern.DATE;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements IProductService {
    ProductRepo productRepo;
    CategoryRepo categoryRepo;
    ProductImageRepo productImageRepo;
    ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductReq productReq) {
        Category category = categoryRepo.findById(productReq.getCategoryId())
                .orElseThrow(() -> new ShopAppException(ErrorCode.CATEGORY_3002));
        Product product = productMapper.toProduct(productReq);
        product.setCategory(category);
        Product productSaved = productRepo.save(product);
        return productMapper.toProductResponse(productSaved);
    }

    @Override
    public List<ProductImage> uploadImage(Long id, List<MultipartFile> files) throws IOException {
        List<ProductImage> productImages = new ArrayList<>();
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.PRODUCT_3002));
        if (files == null) throw new RuntimeException("Files is null");
        for (MultipartFile file : files) {
            if (files.size() > MAXIMUM_IMAGES_PER_PRODUCT)
                throw new ShopAppException(ErrorCode.FILE_2003);
            if (file.getSize() > 10 * 1048576) {
                throw new ShopAppException(ErrorCode.FILE_2002);
            }
            String fileName = storeFile(file);
            ProductImage productImage = createProductImage(product, fileName);
            productImages.add(productImage);
        }
        return productImages;
    }

    @Override
    public ProductResponse getProductById(long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.PRODUCT_3002));
        return productMapper.toProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
        return productRepo.findAll(pageRequest).map(productMapper::toProductResponse);
    }

    @Override
    public void updateProduct(long id, ProductReq productReq) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.PRODUCT_3002));
        Product newProduct = productMapper.toProduct(productReq);
        newProduct.setId(product.getId());
        Category category = categoryRepo.findById(productReq.getCategoryId())
                .orElseThrow(() -> new ShopAppException(ErrorCode.CATEGORY_3002));
        newProduct.setCategory(category);
        productRepo.save(newProduct);
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.PRODUCT_3002));
        productRepo.delete(product);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepo.existsByName(name);
    }

    public ProductImage createProductImage(Product product, String url) {
        ProductImage productImage = ProductImage.builder().product(product).imageUrl(url).build();
        int imageSize = productImageRepo.findByProductId(product.getId()).size();
        if (imageSize >= MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new ShopAppException(ErrorCode.FILE_2003);
        } else {
            return productImageRepo.save(productImage);
        }
    }

    private String storeFile(MultipartFile file) throws IOException {
        if (!isImageFile(file) || file.getOriginalFilename() == null) {
            throw new IOException("Invalid image format");
        }
        String uniqueFileName = generateUniqueName();
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    private String generateUniqueName() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE);
        String timestamp = dateFormat.format(new Date());
        return sb.append(timestamp).append(".png").toString();
    }
}
