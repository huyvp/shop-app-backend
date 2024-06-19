package com.app.shop.service.impl;

import com.app.shop.dto.product.ProductDTO;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.ProductMapper;
import com.app.shop.mapper.UserMapper;
import com.app.shop.models.Category;
import com.app.shop.models.Product;
import com.app.shop.models.ProductImage;
import com.app.shop.repo.CategoryRepository;
import com.app.shop.repo.ProductImageRepository;
import com.app.shop.repo.ProductRepository;
import com.app.shop.response.ProductResponse;
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
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductImageRepository productImageRepository;
    ProductMapper productMapper;
    private final UserMapper userMapper;

    @Override
    public ProductResponse createProduct(ProductDTO productDTO) throws IOException {
        categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ShopAppException(ErrorCode.CATEGORY_3002));
        Product product = productRepository.save(productMapper.toProduct(productDTO));
        return productMapper.toProductResponse(product);
    }

    @Override
    public List<ProductImage> uploadImage(Long id, List<MultipartFile> files) throws IOException {
        List<ProductImage> productImages = new ArrayList<>();
        Product product = productRepository.findById(id)
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
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.PRODUCT_3002));
        return productMapper.toProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(productMapper::toProductResponse);
    }

    @Override
    public Product updateProduct(long id, ProductDTO productDTO) {
        productRepository.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.PRODUCT_3002));
        Product newProduct = productMapper.toProduct(productDTO);
        newProduct.setId(id);
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ShopAppException(ErrorCode.CATEGORY_3002));
        newProduct.setCategory(category);
        return productRepository.save(newProduct);
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.PRODUCT_3002));
        productRepository.delete(product);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    public ProductImage createProductImage(Product product, String url) {
        ProductImage productImage = ProductImage.builder().product(product).imageUrl(url).build();
        int imageSize = productImageRepository.findByProductId(product.getId()).size();
        if (imageSize >= MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new ShopAppException(ErrorCode.FILE_2003);
        } else {
            return productImageRepository.save(productImage);
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
