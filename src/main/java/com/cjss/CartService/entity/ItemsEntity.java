package com.cjss.CartService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items_ordered")
public class ItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;



    private  String skuCode;
    private  Integer quantity;
    private  Double price;
  @JsonBackReference
    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    @ManyToOne( cascade = CascadeType.ALL)
    private OrderEntity orderEntity;


    public ItemsEntity(String skuCode, Integer quantity, OrderEntity or1, Double skuPrice) {
        this.skuCode = skuCode;
        this.quantity = quantity;
        this.price = price;
        this.orderEntity = or1;
    }


    public ItemsEntity(String skuCode, Integer quantity, Double price) {
        this.skuCode = skuCode;
        this.quantity = quantity;
        this.price = price;
    }
}
