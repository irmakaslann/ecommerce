package com.urunsatisi.urunsatisi.controller;

import com.urunsatisi.urunsatisi.service.ProductService;
import com.urunsatisi.urunsatisi.dto.ProductDto;
import com.urunsatisi.urunsatisi.entities.Product;
import com.urunsatisi.urunsatisi.request.ProductRequest;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController

@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/products") // Specify the endpoint path
    public List<ProductDto> getAllProducts() {

        List<Product> products = productService.getAllProducts();


        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        Product product=productService.getProductById(id);
        return modelMapper.map(product,ProductDto.class);

    }

    @PostMapping("/add")
    public ProductDto addProduct(@RequestBody @Validated ProductDto productDto) {
        Product product=productService.addProduct(modelMapper.map(productDto,Product.class));
        return modelMapper.map(product, ProductDto.class);
       // return productService.addProduct(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id,@RequestBody ProductRequest request) {
        productService.deleteProduct(id,request);
    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);

        product.setId(id);

        Product updatedProduct = productService.updateProductById(id, product);
        return modelMapper.map(updatedProduct, ProductDto.class);




    }

}
