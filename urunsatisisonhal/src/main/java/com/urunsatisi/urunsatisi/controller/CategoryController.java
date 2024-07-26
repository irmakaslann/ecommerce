package com.urunsatisi.urunsatisi.controller;

import com.urunsatisi.urunsatisi.service.CategoryService;
import com.urunsatisi.urunsatisi.dto.CategoryDto;
import com.urunsatisi.urunsatisi.entities.Category;
import com.urunsatisi.urunsatisi.request.CategoryRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/categories") // Add a path to the endpoint if needed
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryService.getAllCategory();


        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public Optional<CategoryDto> getCategory(@PathVariable Long id) {

        Optional<Category> category=categoryService.getCategoryById(id);
        return Optional.ofNullable(modelMapper.map(category, CategoryDto.class));

    }

    //defensive programming

    /**
     * Dto al ve client'a DTO dön !
     */
    @PostMapping("/add")
    public CategoryDto add(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.add(modelMapper.map(categoryDto, Category.class));// servise direkt nesneyi göndermelisin !
        return modelMapper.map(category, CategoryDto.class);
    }

    @DeleteMapping("/delete/{id}") // path pattern düzeltildi
    public void delete(@PathVariable Long id, @RequestBody CategoryRequest request) {
        categoryService.delete(id, request);
    }

    @PutMapping("/category/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category updatedCategory = categoryService.updateCategory(id, category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }
}
