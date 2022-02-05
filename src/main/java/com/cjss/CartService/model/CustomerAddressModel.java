package com.cjss.CartService.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressModel {
//    @NotNull(message = "it is not null")
//    @NotEmpty(message = "it is not empty")
    private  String code;
//    @NotNull(message = "it is not null")
//    @NotEmpty(message = "it is not empty")
    private  String state;
//    @NotNull(message = "it is not null")
//    @NotEmpty(message = "it is not empty")
    private String  city;
//    @NotNull(message = "it is not null")
//    @NotEmpty(message = "it is not empty")
    private Boolean  shippingAddress;
//    @NotNull(message = "it is not null")
//    @NotEmpty(message = "it is not empty")
    private Boolean billingAddress;

}