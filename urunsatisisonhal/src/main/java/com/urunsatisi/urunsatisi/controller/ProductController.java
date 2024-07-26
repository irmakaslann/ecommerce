package com.urunsatisi.urunsatisi.controller;

import com.urunsatisi.urunsatisi.service.ProductService;
import com.urunsatisi.urunsatisi.model.dto.ProductDto;
import com.urunsatisi.urunsatisi.model.Product;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return modelMapper.map(product, ProductDto.class);
    }

    @PostMapping("/add")
    public ProductDto addProduct(@RequestBody @Validated ProductDto productDto) {
        Product product = productService.addProduct(modelMapper.map(productDto, Product.class));
        return modelMapper.map(product, ProductDto.class);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        product.setId(id);
        Product updatedProduct = productService.updateProductById(id, product);
        return modelMapper.map(updatedProduct, ProductDto.class);
    }

}
