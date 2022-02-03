package com.cjss.CartService.repository;

import com.cjss.CartService.entity.BillingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAddressRepository  extends JpaRepository<BillingAddressEntity,Integer> {
}
