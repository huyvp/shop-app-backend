package com.app.shop.service.impl;

import com.app.shop.dto.ProductDTO;
import com.app.shop.exception.DataNotFoundException;
import com.app.shop.exception.FileSizeException;
import com.app.shop.models.Category;
import com.app.shop.models.Product;
import com.app.shop.models.ProductImage;
import com.app.shop.repo.CategoryRepository;
import com.app.shop.repo.ProductImageRepository;
import com.app.shop.repo.ProductRepository;
import com.app.shop.response.ProductResponse;
import com.app.shop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

import static com.app.shop.constant.Constants.Common.MAXIMUM_IMAGES_PER_PRODUCT;
import static com.app.shop.constant.Constants.Pattern.DATE;

@Service
@Transactional
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productImageRepository = productImageRepository;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) throws IOException {
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new DataNotFoundException("Cannot find category of product"));
        Product product = Product.builder().name(productDTO.getName()).price(productDTO.getPrice()).description(productDTO.getDescription()).thumbnail(productDTO.getThumbnail()).category(category).build();
        return productRepository.save(product);
    }

    @Override
    public List<ProductImage> uploadImage(Long id, List<MultipartFile> files) throws IOException {
        List<ProductImage> productImages = new ArrayList<>();
        Product product = productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Product of this image not found"));
        if (files == null) throw new RuntimeException("Files is null");
        for (MultipartFile file : files) {
            if (files.size() > MAXIMUM_IMAGES_PER_PRODUCT)
                throw new RuntimeException("Too many images, maximum image price is five");
            if (file.getSize() > 10 * 1048576) {
                throw new FileSizeException("File is too large! Maximum size is 10MB");
            }
            String fileName = storeFile(file);
            ProductImage productImage = createProductImage(product, fileName);
            productImages.add(productImage);
        }
        return productImages;
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can not found product with this id"));
    }

    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(ProductResponse::export);
    }

    @Override
    public Product updateProduct(long id, ProductDTO productDTO) {
        Product product = getProductById(id);
        if (product != null) {
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setThumbnail(productDTO.getThumbnail());
            product.setDescription(productDTO.getDescription());
            Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new DataNotFoundException("Cannot find category of product"));
            product.setCategory(category);
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(productRepository::delete);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    public ProductImage createProductImage(Product product, String url) {
        ProductImage productImage = ProductImage.builder().product(product).imageUrl(url).build();
        int imageSize = productImageRepository.findByProductId(product.getId()).size();
        if (imageSize >= MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new RuntimeException("Too many images, maximum image price is five");
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
