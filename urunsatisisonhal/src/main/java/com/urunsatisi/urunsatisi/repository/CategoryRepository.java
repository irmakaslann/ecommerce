package com.urunsatisi.urunsatisi.repository;

import com.urunsatisi.urunsatisi.entities.Category;
import com.urunsatisi.urunsatisi.request.CategoryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    void deleteById(Long id);
    boolean existsByName(String categoryName);
}
