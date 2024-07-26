package com.urunsatisi.urunsatisi.controller;

import com.urunsatisi.urunsatisi.service.CartService;
import com.urunsatisi.urunsatisi.dto.CartDto;
import com.urunsatisi.urunsatisi.entities.Cart;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final ModelMapper modelMapper;

    @GetMapping("/id")
    public Optional<CartDto> getCartById(@PathVariable Long id) {
        Optional<Cart> cart=cartService.getCartById(id);
        return Optional.ofNullable(modelMapper.map(cart, CartDto.class));

    }

    public List<CartDto> getAllCart(@PathVariable int id) {
        List<Cart> carts=cartService.getAllCart(id);
        return carts.stream()
                .map(cart->modelMapper.map(cart,CartDto.class))
                .collect(Collectors.toList());

    }
    @DeleteMapping("/delete/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
    @PutMapping("/products/{id}")
    public CartDto updateCart(@PathVariable Long id, @RequestBody CartDto cartDto) {
        Cart cart=modelMapper.map(cartDto, Cart.class);
        Cart updateCart=cartService.updateCartById(id,cart);
        return modelMapper.map(updateCart,CartDto.class);

    }
    @PostMapping("/add")
    public CartDto addProduct(@RequestBody CartDto cartDto) {

        Cart cart = modelMapper.map(cartDto, Cart.class);


        Optional<Cart> savedCart = cartService.addCartById(cart);


        return modelMapper.map(savedCart, CartDto.class);
    }




}
