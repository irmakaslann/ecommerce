package com.urunsatisi.urunsatisi.service.impl;


import com.urunsatisi.urunsatisi.service.CategoryService;
import com.urunsatisi.urunsatisi.entities.Category;
import com.urunsatisi.urunsatisi.repository.CategoryRepository;
import com.urunsatisi.urunsatisi.request.CategoryRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private  final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Category> getAllCategory() {
        List<Category> category = categoryRepository.findAll();
        return Collections.singletonList(modelMapper.map(category, Category.class));
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        return category.map(c -> modelMapper.map(c, Category.class));

    }

    @Override
    public Category add(Category category) {
        categoryNameShouldNotRepeatWhenCategoryAdd(category.getName());

        category.builder().name(category.getName()).build();//
        Category savedCategory = categoryRepository.save(category);//


        return modelMapper.map(savedCategory, Category.class);
    }

    @Override
    public void delete(Long id, CategoryRequest request) {
        categoryNotFound(request.getCategoryId());

        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));


        existingCategory.setName(category.getName());



        Category updatedCategory = categoryRepository.save(existingCategory);

        return updatedCategory;
    }




    @Override
    public void categoryNotFound(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category not found.");
        }
    }

    private void categoryNameShouldNotRepeatWhenCategoryAdd(String categoryName){

        if (categoryRepository.existsByName(categoryName)) {
            throw new IllegalArgumentException("Category name must be unique.");
        }
    }
    }




