package com.urunsatisi.urunsatisi.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private int stock;
    private double price;
    private Long categoryId;

}
