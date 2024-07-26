package com.urunsatisi.urunsatisi.service;

import com.urunsatisi.urunsatisi.entities.Cart;

import java.util.List;
import java.util.Optional;

 public interface CartService {
     Optional<Cart> getCartById(Long id);
     List<Cart> getAllCart(int id);
     void deleteCart(Long id);
     Cart updateCartById(Long id,Cart cart);
     Optional<Cart> addCartById(Cart cart);
}
