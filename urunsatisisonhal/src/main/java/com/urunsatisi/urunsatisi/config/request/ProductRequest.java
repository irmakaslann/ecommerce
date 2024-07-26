package com.urunsatisi.urunsatisi.config.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ProductRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String productName;

    @NotNull
    private int stock;

    @NotNull
    private double price;

    @NotNull
    private Long categoryId;

}
