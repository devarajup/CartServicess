package com.cjss.CartService.entity;

import com.cjss.CartService.util.CustomCustomerIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    public OrderEntity(String orderCode, String orderStatus) {
        this.orderCode = orderCode;
        this.orderStatus = orderStatus;
    }

    @Id
    @GenericGenerator(name = "order_code_gen",
            strategy = "com.cjss.CartService.util.CustomCustomerIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.VALUE_PREFIX_PARAMETER, value = "order#"),
                    @org.hibernate.annotations.Parameter(name = CustomCustomerIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") }
    )
    @GeneratedValue(generator = "order_code_gen",strategy = GenerationType.IDENTITY)
    private  String orderCode;
    private String orderStatus;
    @OneToMany(mappedBy = "orderEntity")
    private List<ItemsOrdered> itemsOrderedList;

}
