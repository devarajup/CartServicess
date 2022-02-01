package com.cjss.CartService.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "items_ordered")
public class ItemsOrdered {
    @Id

    private String itemId;
    private  String skuCode;
    private  Integer quantity;
    private  Double price;

    @ManyToOne(cascade  =CascadeType.ALL)
    @JoinColumn(name = "order_code")
    private OrderEntity orderEntity;
}
