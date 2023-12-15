package com.example.btl.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private float price;
    private String texture;
    private float weight;
    private String size;
    private String categoryName;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductCart> productCarts;

}
