package com.example.btl.repository;

import com.example.btl.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductCartRepository extends JpaRepository<ProductCart,Long> {
        void deleteByCart_Id(Long cartId);

        void deleteByProduct_Id(Long productId);



}
