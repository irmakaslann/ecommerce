package com.urunsatisi.urunsatisi.request;

public class CartRequest {
    private String cartName;
    private Long cartId;

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}