package com.cjss.CartService.repository;

import com.cjss.CartService.entity.ItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsEntity,String> {

}
