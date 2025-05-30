package com.spring.boot.repo;
import com.spring.boot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepo  extends JpaRepository<Product, Long> {

    List<Product>  findProductsByCategoryId(Long categoryId);
    Optional<Product> findByName(String name);
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);



}
