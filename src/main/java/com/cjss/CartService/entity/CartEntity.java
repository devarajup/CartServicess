package com.cjss.CartService.entity;

import com.cjss.CartService.util.CustomCustomerIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_Table")
public class CartEntity {
    @Id
    @GenericGenerator(name = "cart_id_gen",
            strategy = "com.cjss.CartService.util.CustomCustomerIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.VALUE_PREFIX_PARAMETER, value = "I"),
                    @org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") }
    )
    @GeneratedValue(generator = "cart_id_gen",strategy = GenerationType.IDENTITY)
    @Column(unique = false)
    private String carId;

    private String email;
    private String productCode;
    private  String skuCode;
    private  Integer quantity;

}
