package com.urunsatisi.urunsatisi.service;

import com.urunsatisi.urunsatisi.entities.Product;
import com.urunsatisi.urunsatisi.request.ProductRequest;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProductById(Long id);
    public Product addProduct(Product product);
    public void deleteProduct(Long id, ProductRequest request);
    public Product updateProductById(Long id, Product product);
}
