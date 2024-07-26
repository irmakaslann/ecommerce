package com.urunsatisi.urunsatisi.service.impl;

import com.urunsatisi.urunsatisi.service.CartService;
import com.urunsatisi.urunsatisi.entities.Cart;
import com.urunsatisi.urunsatisi.repository.CartRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private ModelMapper modelMapper;

    @Override
    public Optional<Cart> getCartById(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        return optionalCart.map(cart -> modelMapper.map(cart, Cart.class));
    }

    @Override
    public List<Cart> getAllCart(int id) {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream()
                .map(cart -> modelMapper.map(cart, Cart.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart updateCartById(Long id, Cart cart) {
        Optional<Cart> idCart = cartRepository.findById(id);

        if (idCart.isPresent()) {
            Cart existingCart = idCart.get();
            modelMapper.map(cart, existingCart);

            Cart updatedCart = cartRepository.save(existingCart);

            return updatedCart;
        }
        return null;
    }


    @Override
    public Optional<Cart> addCartById(Cart cart) {
       Cart savedCart = cartRepository.save(cart);
       return Optional.ofNullable(modelMapper.map(savedCart, Cart.class));
    }
}