package com.cjss.CartService.entity;

import com.cjss.CartService.util.CustomCustomerIdGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items_ordered")
public class ItemsEntity {

    @Id
    @GenericGenerator(name = "item_id_gen", strategy = "com.cjss.CartService.util.CustomCustomerIdGenerator", parameters = {@org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.VALUE_PREFIX_PARAMETER, value = "ITM"), @org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")})
    @GeneratedValue(generator = "item_id_gen", strategy = GenerationType.IDENTITY)
    private String itemId;
    private String ItemStatus;
    private  String skuCode;
    private  Integer quantity;
    private  Double price;
/*
  @JsonBackReference
    public OrderEntity getOrderEntity() {
        return orderEntity;
    }
*/

    @ManyToOne( cascade = CascadeType.ALL )
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
