package com.example.btl.service;

import com.example.btl.dto.CartDto;
import com.example.btl.entity.Cart;

public interface CartService {

    void createCart(Long userID, Cart cart);
    void addProductToCart(Long cartId, Long productId);

    CartDto getCartById(Long cartId);

    void deleteCartById(Long id);
}
