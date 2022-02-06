package com.cjss.CartService.model;

import com.cjss.CartService.entity.BillingAddressEntity;
import com.cjss.CartService.entity.ItemsEntity;
import com.cjss.CartService.entity.ShippingAddressEntity;
import com.cjss.CartService.util.CustomCustomerIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    private String orderCode;
    private List<ItemsModel> itemsList;
    private ShippingAddressModel shippingAddressModel;
    private BillingAddressModel billingAddressModel;

}
