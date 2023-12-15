package com.example.btl.service;

import com.example.btl.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto getProductById(Long id);

    List<ProductDto> getAllProducts();

    ProductDto updateProductById(ProductDto productDto, Long id);

    void deleteProductById(Long id);

}
