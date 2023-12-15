package com.example.btl.repository;

import com.example.btl.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCartRepository extends JpaRepository<ProductCart,Long> {
        int countByCartId(Long cartId);
}
