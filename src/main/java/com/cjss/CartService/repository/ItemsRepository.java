package com.cjss.CartService.repository;

import com.cjss.CartService.entity.ItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsEntity,String> {

    ItemsEntity findByitemId(String itemCode);
    @Transactional
    @Modifying
    @Query("UPDATE ItemsEntity SET ItemStatus = :ItemStatus WHERE itemId=:itemId")
    void updateByItemStaus(String itemId,String ItemStatus);

    @Query(value = "SELECT * FROM ecommerce_project_db.items_ordered where  item_id=:itemId and order_entity_order_code=:OrderId",nativeQuery = true)
    Optional<ItemsEntity> checkOrderIdInItem(String OrderId, String itemId);

    @Query(value = "SELECT item_id FROM ecommerce_project_db.items_ordered where  order_entity_order_code=:OrderId",nativeQuery = true)
    List<String> getItemsByOrderId(String OrderId);
}
