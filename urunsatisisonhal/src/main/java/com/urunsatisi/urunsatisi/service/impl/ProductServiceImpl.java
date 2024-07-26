package com.urunsatisi.urunsatisi.service.impl;


import com.urunsatisi.urunsatisi.entities.Category;
import com.urunsatisi.urunsatisi.service.CategoryService;
import com.urunsatisi.urunsatisi.service.ProductService;
import com.urunsatisi.urunsatisi.entities.Product;
import com.urunsatisi.urunsatisi.repository.ProductRepository;
import com.urunsatisi.urunsatisi.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Override
    public List<Product> getAllProducts() {
        List<Product> getProduct = productRepository.findAll();
        return Collections.singletonList(modelMapper.map(getProduct, Product.class));

    }

    @Override
    public Product getProductById(Long id) {

        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product addProduct(Product product) {
        categoryService.categoryNotFound(product.getId());

        categoryExists(product.getName());

        Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(product.getCategory())
                .build();
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id, ProductRequest request) {
        categoryforDelete(request.getProductName());
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            log.error("Product not found with id : {}", product.getId());
           // throw new SourceNotFoundException("Product not found");
        }
        optionalProduct.get();
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        product.setStock(product.getStock());
        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, Product.class);
    }
    private void categoryExists(String name){
        if(productRepository.existsByName(name)){
            throw new IllegalArgumentException("product already exists");
        }
    }

    private void categoryforDelete(String name){
        if(name.isEmpty()){
            throw new IllegalArgumentException("Category  cannot be empty");
        }
    }
}
