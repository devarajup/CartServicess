package com.cjss.CartService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartModel {
    public CartModel(String email, String productCode, String skuCode, Integer quantity) {
        this.email = email;
        this.productCode = productCode;
        this.skuCode = skuCode;
        this.quantity = quantity;
    }

    private  String email;
    private String productCode;
    private  String skuCode;
    private  Integer quantity;
    private BillingAddressModel billingAddressModel;
    private ShippingAddressModel shippingAddressModel;
}
