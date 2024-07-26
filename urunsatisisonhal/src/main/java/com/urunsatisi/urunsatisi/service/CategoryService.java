package com.urunsatisi.urunsatisi.service;

import com.urunsatisi.urunsatisi.entities.Category;
import com.urunsatisi.urunsatisi.request.CategoryRequest;

import java.util.List;
import java.util.Optional;

 public interface CategoryService {
     List<Category> getAllCategory();
     Optional<Category> getCategoryById(Long id);
     Category add(Category category);
     void delete(Long id, CategoryRequest request);
     Category updateCategory(Long id, Category category);
;

     void categoryNotFound(Long id);
}
