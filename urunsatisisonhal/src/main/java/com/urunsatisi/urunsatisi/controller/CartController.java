package com.urunsatisi.urunsatisi.controller;

import com.urunsatisi.urunsatisi.model.dto.CartDto;
import com.urunsatisi.urunsatisi.service.CartService;
import com.urunsatisi.urunsatisi.model.Cart;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public CartDto getCartById(@PathVariable Long id) {
        return modelMapper.map(cartService.getCartById(id), CartDto.class);
    }

    @GetMapping("/getAll")
    public List<CartDto> getAllCart() {
        List<Cart> carts = cartService.getAllCart();

        return carts.stream()
                .map(cart -> modelMapper.map(cart, CartDto.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }


    @PostMapping("/add")
    public CartDto addProduct(@RequestBody CartDto cartDto) {
        Cart cart = modelMapper.map(cartDto, Cart.class);
        return modelMapper.map(cartService.addCart(cart), CartDto.class);
    }
}








