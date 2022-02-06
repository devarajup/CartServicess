package com.cjss.CartService.model;

import com.cjss.CartService.entity.OrderEntity;
import com.cjss.CartService.util.CustomCustomerIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsModel {

    private String itemId;
    private String ItemStatus;
    private  String skuCode;
    private  Integer quantity;
    private  Double price;


}
