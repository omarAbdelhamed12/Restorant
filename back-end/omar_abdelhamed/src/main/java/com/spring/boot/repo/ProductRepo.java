package com.spring.boot.repo;
import com.spring.boot.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepo  extends JpaRepository<Product, Long> {

    Page<Product>  findProductsByCategoryId(Long categoryId, Pageable pageable);
    Optional<Product> findByName(String name);
    Page<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description,Pageable pageable);

    Page<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndCategoryId(String name, String description,Long categoryId,Pageable pageable);


    Page<Product> findAllByOrderByIdAsc(Pageable pageable);

    List<Product> findByIdIn(List<Long> ids);
}
