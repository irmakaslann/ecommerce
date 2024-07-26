package com.urunsatisi.urunsatisi.service.impl;

import com.urunsatisi.urunsatisi.config.exception.SourceNotFoundException;
import com.urunsatisi.urunsatisi.service.CartService;
import com.urunsatisi.urunsatisi.model.Cart;
import com.urunsatisi.urunsatisi.repository.CartRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private ModelMapper modelMapper;

    @Override
    public Cart getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new SourceNotFoundException("Cart not found with id : " + id));

        return modelMapper.map(cart, Cart.class);
    }

    @Override
    public List<Cart> getAllCart() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream().map(cart -> modelMapper.map(cart, Cart.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart addCart(Cart cart) {
        if (cart == null) {
            log.error("Cart is null !");
            throw new SourceNotFoundException("Cart not found !");
        }
        return cartRepository.save(cart);
    }
}



