package com.urunsatisi.urunsatisi.service;

import com.urunsatisi.urunsatisi.model.Cart;

import java.util.List;

public interface CartService {
     Cart getCartById(Long id);

     List<Cart> getAllCart();

     void deleteCart(Long id);

     Cart addCart(Cart cart);
}
