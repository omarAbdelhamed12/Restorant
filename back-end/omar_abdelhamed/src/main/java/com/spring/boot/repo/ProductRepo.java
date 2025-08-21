package com.spring.boot.repo;
import com.spring.boot.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepo  extends JpaRepository<Product, Long> {

    Page<Product>  findProductsByCategoryId(Long categoryId, Pageable pageable);
    Optional<Product> findByName(String name);
    Page<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description,Pageable pageable);

    Page<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndCategoryId(String name, String description,Long categoryId,Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND (LOWER(p.name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :searchValue, '%')))")
    Page<Product> searchInCategory(@Param("categoryId") Long categoryId,
                                   @Param("searchValue") String searchValue,
                                   Pageable pageable);

    Page<Product> findAllByOrderByIdAsc(Pageable pageable);

    List<Product> findByIdIn(List<Long> ids);
}
