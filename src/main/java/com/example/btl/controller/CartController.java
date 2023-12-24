package com.example.btl.controller;

import com.example.btl.dto.CartDto;
import com.example.btl.entity.Cart;
import com.example.btl.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class CartController {
    private CartService cartService;

    @PostMapping("users/{userId}/carts")
    public ResponseEntity<String> createCart(@PathVariable Long userId, Cart cart){
        cartService.createCart(userId, cart);
        return ResponseEntity.ok("Create cart successfully");
    }

    @GetMapping("carts/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable Long cartId){
        return ResponseEntity.ok(cartService.getCartById(cartId));
    }

    @PostMapping("carts/{cartId}/products/{productId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long cartId,
                                                   @PathVariable Long productId){
        cartService.addProductToCart(cartId, productId);
        return ResponseEntity.ok("Add product successfully");
    }
    
    @DeleteMapping("carts/{cartId}/products")
    public ResponseEntity<String> deleteProductsInCart(@PathVariable Long cartId){
        cartService.deleteProductsInCart(cartId);
        return ResponseEntity.ok("Products in cart has been delete successfully");
    }
}
