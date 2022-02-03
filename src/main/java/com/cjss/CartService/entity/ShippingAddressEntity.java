package com.cjss.CartService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipping_address")
public class ShippingAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShippingAddressId", nullable = false)
    private Integer ShippingAddressId;

    public ShippingAddressEntity(String code, String state, String city, OrderEntity orderEntity) {
        this.code = code;
        this.state = state;
        this.city = city;
        this.orderEntity = orderEntity;
    }

    private  String code;
    private  String state;
    private String  city;

    @OneToOne(mappedBy = "shippingAddressEntity")
    private OrderEntity orderEntity;
    @JsonBackReference
    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

}
