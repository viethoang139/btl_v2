package com.example.btl.service.impl;

import com.example.btl.dto.CartDto;
import com.example.btl.dto.ProductDto;
import com.example.btl.entity.Cart;
import com.example.btl.entity.Product;
import com.example.btl.entity.ProductCart;
import com.example.btl.entity.User;
import com.example.btl.exception.CartAlreadyExitsException;
import com.example.btl.exception.ResourceNotFoundException;
import com.example.btl.repository.CartRepository;
import com.example.btl.repository.ProductCartRepository;
import com.example.btl.repository.ProductRepository;
import com.example.btl.repository.UserRepository;
import com.example.btl.service.CartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private ProductCartRepository productCartRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Override
    public void createCart(Long userId, Cart cart) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","ID",userId));

        if(user.getCart() != null){
            throw new CartAlreadyExitsException("Cart already exits");
        }
        cart.setUser(user);
        cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).
                orElseThrow(() -> new ResourceNotFoundException("Cart","ID",cartId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product","ID",productId));
        ProductCart productCart = new ProductCart();
        productCart.setCart(cart);
        productCart.setProduct(product);
        productCart.setQuantity(product.getQuantity());
        productCartRepository.save(productCart);
    }

    @Override
    public CartDto getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart","ID",cartId));
        List<Product> products = new ArrayList<>();
        for(ProductCart productCart: cart.getProductCarts()){
            Long productId = productCart.getProduct().getId();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product","ID",productId));
            products.add(product);
        }
        List<ProductDto> productDtos = products.stream().map( product -> modelMapper.map(product,ProductDto.class))
                .collect(Collectors.toList());
        CartDto cartDto = new CartDto();
        cartDto.setId(cartId);
        cartDto.setProductDtos(productDtos);
        return cartDto;
    }


    @Override
    public void deleteCartById(Long id) {
        cartRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Cart","ID",id));
        cartRepository.deleteById(id);
    }
}
