package com.urunsatisi.urunsatisi.service;

import com.urunsatisi.urunsatisi.model.Category;

import java.util.List;

public interface CategoryService {
     List<Category> getAllCategory();

     Category getCategoryById(Long id);

     Category add(Category category);

     void delete(Long id);

     Category updateCategory(Long id, Category category);
}
