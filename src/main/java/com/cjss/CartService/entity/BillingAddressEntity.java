package com.cjss.CartService.entity;

import com.cjss.CartService.util.CustomCustomerIdGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "billing_address")
public class BillingAddressEntity {
    @Id
  /*  @GenericGenerator(name = "bill_id_gen",
            strategy = "om.cjss.CartService.util.CustomCustomerIdGenerator.CustomCustomerIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.VALUE_PREFIX_PARAMETER, value = "BAI"),
                    @org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") }
    )
    @GeneratedValue(generator = "bill_id_gen", strategy = GenerationType.IDENTITY)*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer BillingAddressId;

    private String code;
    private String state;
    private String city;
/*
    @OneToOne(mappedBy = "billingAddressEntity")
    private OrderEntity orderEntity;
*/

    public BillingAddressEntity(String code, String state, String city) {
        this.code = code;
        this.state = state;
        this.city = city;
//        this.orderEntity = orderEntity;
    }



/*    @JsonBackReference
    public OrderEntity getOrderEntity() {
        return orderEntity;
    }*/
}
