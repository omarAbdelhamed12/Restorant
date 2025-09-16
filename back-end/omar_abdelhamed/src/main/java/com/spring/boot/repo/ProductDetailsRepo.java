package com.spring.boot.repo;

import com.spring.boot.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDetailsRepo  extends JpaRepository<ProductDetails, Integer> {
    Optional<ProductDetails> findProductDetailsByProduct_Id(Long id);
}
