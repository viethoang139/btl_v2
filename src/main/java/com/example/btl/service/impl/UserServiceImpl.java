package com.example.btl.service.impl;

import com.example.btl.dto.CartDto;
import com.example.btl.dto.ProductDto;
import com.example.btl.dto.UserDto;
import com.example.btl.entity.Cart;
import com.example.btl.entity.Product;
import com.example.btl.entity.ProductCart;
import com.example.btl.entity.User;
import com.example.btl.exception.ResourceNotFoundException;
import com.example.btl.repository.CartRepository;
import com.example.btl.repository.ProductRepository;
import com.example.btl.repository.UserRepository;
import com.example.btl.service.CartService;
import com.example.btl.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private PasswordEncoder passwordEncoder;
    private CartService cartService;

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User","ID",id));
        UserDto userDto = modelMapper.map(user,UserDto.class);
        Cart cart = cartRepository.findById(user.getCart().getId()).get();
        List<Product> products = new ArrayList<>();
        for(ProductCart productCart : cart.getProductCarts()){
            Product product = productRepository.findById(productCart.getProduct().getId()).get();
            products.add(product);
        }
        List<ProductDto> productDtos = products.stream().map(product -> modelMapper.map(product,ProductDto.class))
                .collect(Collectors.toList());
        CartDto cartDto = new CartDto();
        cartDto.setId(user.getCart().getId());
        cartDto.setProductDtos(productDtos);
        userDto.setCartDto(cartDto);
        return userDto;
    }

    @Override
    public UserDto updateUserById(UserDto userDto, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User","ID",id));
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        User updateUser = userRepository.save(user);
        return modelMapper.map(updateUser,UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User","ID",id));
        cartService.deleteCartById(user.getCart().getId());
        userRepository.deleteById(id);
    }
}
