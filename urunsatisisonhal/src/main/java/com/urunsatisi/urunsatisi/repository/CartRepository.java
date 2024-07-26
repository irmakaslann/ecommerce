package com.urunsatisi.urunsatisi.repository;

import com.urunsatisi.urunsatisi.dto.CartDto;
import com.urunsatisi.urunsatisi.entities.Cart;
import com.urunsatisi.urunsatisi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
