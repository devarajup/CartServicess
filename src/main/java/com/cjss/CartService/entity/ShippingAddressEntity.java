package com.cjss.CartService.entity;

import com.cjss.CartService.util.CustomCustomerIdGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipping_address")
public class ShippingAddressEntity {
    @Id
/*    @GenericGenerator(name = "ship_id_gen",
            strategy = "om.cjss.CartService.util.CustomCustomerIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.VALUE_PREFIX_PARAMETER, value = "SAD"),
                    @org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") }
    )
    @GeneratedValue(generator = "ship_id_gen", strategy = GenerationType.SEQUENCE)*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ShippingAddressId;

    public ShippingAddressEntity(String code, String state, String city) {
        this.code = code;
        this.state = state;
        this.city = city;
    }

    private  String code;
    private  String state;
    private String  city;


   /* @OneToOne
    private OrderEntity orderEntity;
    @JsonBackReference
    public OrderEntity getOrderEntity() {
        return orderEntity;
    }*/

}
