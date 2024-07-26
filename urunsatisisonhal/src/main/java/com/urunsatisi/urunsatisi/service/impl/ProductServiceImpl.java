package com.urunsatisi.urunsatisi.service.impl;


import com.urunsatisi.urunsatisi.config.exception.SourceNotFoundException;

import com.urunsatisi.urunsatisi.service.ProductService;
import com.urunsatisi.urunsatisi.model.Product;
import com.urunsatisi.urunsatisi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Product addProduct(Product product) {
        categoryExists(product.getName());
        return productRepository.save(product);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProduct(Long id) {
        Product savedProduct = getProduct(id);
        productRepository.delete(savedProduct);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Product updateProductById(Long id, Product product) {
        Product updatedProduct = getProduct(id);

        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setStock(product.getStock());
        productRepository.save(updatedProduct);

        return modelMapper.map(updatedProduct, Product.class);
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new SourceNotFoundException("Product not found with id : " + id));
    }

    private void categoryExists(String name) {
        if (productRepository.existsByName(name)) {
            throw new IllegalArgumentException("Product already exists with name : " + name);
        }
    }

}





