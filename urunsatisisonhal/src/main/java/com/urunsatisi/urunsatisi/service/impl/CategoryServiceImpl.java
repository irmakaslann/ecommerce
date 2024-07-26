package com.urunsatisi.urunsatisi.service.impl;


import com.urunsatisi.urunsatisi.config.exception.SourceNotFoundException;
import com.urunsatisi.urunsatisi.service.CategoryService;
import com.urunsatisi.urunsatisi.model.Category;
import com.urunsatisi.urunsatisi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new SourceNotFoundException("Category not found"));
    }

    @Override
    public Category add(Category category) {
        Category savedCategory = categoryRepository.findByName(category.getName());

        if (savedCategory != null) {
            log.error("Category already exists with name {}", savedCategory.getName());
            throw new IllegalArgumentException("Category already exists");
        }

        log.info("Category succesffully created with id {}", category.getId());
        return modelMapper.map(categoryRepository.save(category), Category.class);
    }

    @Override
    public void delete(Long id) {
        Category category = checkCategory(id);
        log.info("Category deleted with id: {}", category.getId());

        categoryRepository.delete(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = checkCategory(id);
        existingCategory.setName(category.getName());

        return categoryRepository.save(existingCategory);
    }

    private Category checkCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

}




