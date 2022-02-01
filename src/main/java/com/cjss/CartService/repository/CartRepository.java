package com.cjss.CartService.repository;

import com.cjss.CartService.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository   extends  JpaRepository<CartEntity,String> {
    List<CartEntity> findAllByEmail(String email);

    boolean existsByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE CartEntity  c SET quantity= :qty WHERE c.email=:email AND c.skuCode=:skuCode")
    Integer upadateByEmailAndSkuCode(String email, String skuCode,Integer qty);

    boolean existsBySkuCode(String skuCode);
}
