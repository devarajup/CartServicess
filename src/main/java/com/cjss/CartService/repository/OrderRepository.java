package com.cjss.CartService.repository;

import com.cjss.CartService.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity  o SET orderStatus= :orderStatus WHERE o.orderCode=:orderCode")
    void updateOrderStatus(String orderCode, String orderStatus);
}
