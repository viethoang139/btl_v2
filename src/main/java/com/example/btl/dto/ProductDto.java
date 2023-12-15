package com.example.btl.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotEmpty(message = "name can not be empty")
    private String name;
    private String description;
    @PositiveOrZero
    private int quantity;
    @NotNull(message = "price can not be null")
    @DecimalMin(value = "0.0")
    private float price;
    @NotEmpty(message = "texture can not be empty")
    private String texture;
    @NotNull(message = "weight can not be null")
    @DecimalMin(value = "0.0")
    private float weight;
    @NotEmpty(message = "size can not be empty")
    private String size;
    @NotEmpty(message = "categoryName can not be empty")
    private String categoryName;
}
