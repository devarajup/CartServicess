package com.cjss.CartService.repository;

import com.cjss.CartService.entity.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipingAddressRepository extends JpaRepository<ShippingAddressEntity,Integer> {
}
