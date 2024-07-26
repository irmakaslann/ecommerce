package com.urunsatisi.urunsatisi.controller;

import com.urunsatisi.urunsatisi.service.CategoryService;
import com.urunsatisi.urunsatisi.model.dto.CategoryDto;
import com.urunsatisi.urunsatisi.model.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @GetMapping("/getAll")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return modelMapper.map(category, CategoryDto.class);
    }

    @PostMapping("/add")
    public CategoryDto add(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.add(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(category, CategoryDto.class);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryService.updateCategory(id, category);
        return ResponseEntity.ok().body("Successfully updated Category");
    }
}







