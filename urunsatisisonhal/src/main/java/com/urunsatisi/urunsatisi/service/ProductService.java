package com.urunsatisi.urunsatisi.service;

import com.urunsatisi.urunsatisi.model.Product;

import java.util.List;

public interface ProductService {
     List<Product> getAllProducts();

     Product getProductById(Long id);

     Product addProduct(Product product);

     void deleteProduct(Long id);

     Product updateProductById(Long id, Product product);
}
