package com.cjss.CartService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartModel {
    public CartModel(String productCode, String skuCode, Integer quantity) {
        this.productCode = productCode;
        this.skuCode = skuCode;
        this.quantity = quantity;
    }
    @NotNull(message = "email required")
    private  String email;
    @NotNull(message = "productCode required")
    private String productCode;
    @NotNull(message = "skuCode required")
    private  String skuCode;
    @NotNull(message = "quantity required")
    private  Integer quantity;
    private BillingAddressModel billingAddressModel;
    private ShippingAddressModel shippingAddressModel;
}
