package com.spring.boot.repo;

import com.spring.boot.model.Order;
import com.spring.boot.vm.OrderProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUserIdOrderByIdAsc(Long userId);
//    @Query("SELECT o.id AS orderId, o.code AS orderCode, o.totalPrice AS totalPrice, o.totalNumber AS totalNumber, p.name AS productName " +
//            "FROM Order o JOIN o.products p " +
//            "WHERE o.user.id = :userId")
//    List<OrderProductProjection> findOrdersWithProductsByUserId(@Param("userId") Long userId);

}
