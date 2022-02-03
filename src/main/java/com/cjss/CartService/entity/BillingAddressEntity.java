package com.cjss.CartService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "billing_address")
public class BillingAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer BillingAddressId;

    private String code;
    private String state;
    private String city;
    @OneToOne(mappedBy = "billingAddressEntity")
    private OrderEntity orderEntity;

    public BillingAddressEntity(String code, String state, String city, OrderEntity orderEntity) {
        this.code = code;
        this.state = state;
        this.city = city;
        this.orderEntity = orderEntity;
    }

    @JsonBackReference
    public OrderEntity getOrderEntity() {
        return orderEntity;
    }
}
